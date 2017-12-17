package emperatriz.deipno.player

import android.content.Context
import android.graphics.*
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import java.util.HashSet

class SlidePuzzleView(context: Context, private val slidePuzzle: SlidePuzzle?) : View(context) {

    var bitmap: Bitmap? = null
        set(bitmap) {
            field = bitmap
            puzzleWidth = 0
            puzzleHeight = 0
        }
    private val sourceRect: Rect
    private val targetRect: RectF
    var targetWidth: Int = 0
        private set
    var targetHeight: Int = 0
        private set
    private var targetOffsetX: Int = 0
    private var targetOffsetY: Int = 0
    private var puzzleWidth: Int = 0
    private var puzzleHeight: Int = 0
    private var targetColumnWidth: Int = 0
    private var targetRowHeight: Int = 0
    private var sourceColumnWidth: Int = 0
    private var sourceRowHeight: Int = 0
    private var sourceWidth: Int = 0
    private var sourceHeight: Int = 0
    private var dragging: MutableSet<Int>? = null
    private var dragStartX: Int = 0
    private var dragStartY: Int = 0
    private var dragOffsetX: Int = 0
    private var dragOffsetY: Int = 0
    private var dragDirection: Int = 0
    val showNumbers = ShowNumbers.NONE
    private val textPaint: Paint
    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0
    private val framePaint: Paint
    private var dragInTarget = false
    private var tiles: IntArray? = null
    private val tilePaint: Paint

    enum class ShowNumbers {
        NONE, SOME, ALL
    }

    init {

        sourceRect = Rect()
        targetRect = RectF()

        tilePaint = Paint()
        tilePaint.isAntiAlias = true
        tilePaint.isDither = true
        tilePaint.isFilterBitmap = true

        textPaint = Paint()
        textPaint.setARGB(0xff, 0xff, 0xff, 0xff)
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 20f
        textPaint.typeface = Typeface.DEFAULT_BOLD
        textPaint.setShadowLayer(1f, 2f, 2f, -0x1000000)

        framePaint = Paint()
        framePaint.setARGB(0x00, 0x80, 0x80, 0x80)
        framePaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        puzzleHeight = 0
        puzzleWidth = puzzleHeight
    }

    private fun refreshDimensions() {
        targetWidth = canvasWidth
        targetHeight = canvasHeight

        sourceWidth = this.bitmap!!.width
        sourceHeight = this.bitmap!!.height

        val targetRatio = targetWidth.toDouble() / targetHeight.toDouble()
        val sourceRatio = sourceWidth.toDouble() / sourceHeight.toDouble()

        targetOffsetX = 0
        targetOffsetY = 0

        if (sourceRatio > targetRatio) {
            val newTargetHeight = (targetWidth / sourceRatio).toInt()
            val delta = targetHeight - newTargetHeight
            targetOffsetY = delta / 2
            targetHeight = newTargetHeight
        } else if (sourceRatio < targetRatio) {
            val newTargetWidth = (targetHeight * sourceRatio).toInt()
            val delta = targetWidth - newTargetWidth
            targetOffsetX = delta / 2
            targetWidth = newTargetWidth
        }

        puzzleWidth = slidePuzzle!!.width
        puzzleHeight = slidePuzzle!!.height

        targetColumnWidth = targetWidth / puzzleWidth
        targetRowHeight = targetHeight / puzzleHeight
        sourceColumnWidth = sourceWidth / puzzleWidth
        sourceRowHeight = sourceHeight / puzzleHeight
    }

    override fun onDraw(canvas: Canvas) {
        if (slidePuzzle == null || this.bitmap == null) {
            return
        }

        if (puzzleWidth != slidePuzzle!!.width || puzzleHeight != slidePuzzle!!.height) {
            refreshDimensions()
        }

        val solved = slidePuzzle!!.isSolved
        canvas.drawColor(if (solved) COLOR_SOLVED else COLOR_ACTIVE)

        val originalTiles = slidePuzzle!!.getTiles()

        if (tiles == null || tiles!!.size != originalTiles!!.size) {
            tiles = IntArray(originalTiles!!.size)
        }

        for (i in tiles!!.indices) {
            if (originalTiles!![i] == originalTiles.size - 1) {
                continue
            }

            if (dragInTarget && dragging!!.contains(i)) {
                tiles!![i - SlidePuzzle.DIRECTION_X[dragDirection] - puzzleWidth * SlidePuzzle.DIRECTION_Y[dragDirection]] = originalTiles[i]
            } else {
                tiles!![i] = originalTiles[i]
            }
        }

        val delta = if (!dragInTarget) 0 else (SlidePuzzle.DIRECTION_X[dragDirection] + puzzleWidth * SlidePuzzle.DIRECTION_Y[dragDirection]) * dragging!!.size
        val shownHandleLocation = slidePuzzle!!.handleLocation + delta
        tiles!![shownHandleLocation] = tiles!!.size - 1

        val emptyTile = tiles!!.size - 1

        for (i in tiles!!.indices) {
            if (!solved && originalTiles!![i] == emptyTile) {
                continue
            }

            val targetColumn = slidePuzzle!!.getColumnAt(i)
            val targetRow = slidePuzzle!!.getRowAt(i)

            val sourceColumn = slidePuzzle!!.getColumnAt(originalTiles!![i])
            val sourceRow = slidePuzzle!!.getRowAt(originalTiles!![i])

            targetRect.left = (targetOffsetX + targetColumnWidth * targetColumn).toFloat()
            targetRect.top = (targetOffsetY + targetRowHeight * targetRow).toFloat()
            targetRect.right = if (targetColumn < puzzleWidth - 1) targetRect.left + targetColumnWidth else targetOffsetX.toFloat() + targetWidth.toFloat()
            targetRect.bottom = if (targetRow < puzzleHeight - 1) targetRect.top + targetRowHeight else targetOffsetY.toFloat() + targetHeight.toFloat()

            sourceRect.left = sourceColumnWidth * sourceColumn
            sourceRect.top = sourceRowHeight * sourceRow
            sourceRect.right = if (sourceColumn < puzzleWidth - 1) sourceRect.left + sourceColumnWidth else sourceWidth
            sourceRect.bottom = if (sourceRow < puzzleHeight - 1) sourceRect.top + sourceRowHeight else sourceHeight

            val isDragTile = dragging != null && dragging!!.contains(i)

            val matchLeft: Boolean
            val matchRight: Boolean
            val matchTop: Boolean
            val matchBottom: Boolean

            var di = i

            if (dragInTarget && dragging!!.contains(i)) {
                di = di - SlidePuzzle.DIRECTION_X[dragDirection] - puzzleWidth * SlidePuzzle.DIRECTION_Y[dragDirection]
            }

            if (di == tiles!![di]) {
                matchBottom = true
                matchTop = matchBottom
                matchRight = matchTop
                matchLeft = matchRight
            } else {
                matchLeft = di - 1 >= 0 && di % puzzleWidth > 0 && tiles!![di] % puzzleWidth > 0 && tiles!![di - 1] == tiles!![di] - 1
                matchRight = tiles!![di] + 1 < tiles!!.size - 1 && (di + 1) % puzzleWidth > 0 && (tiles!![di] + 1) % puzzleWidth > 0 && di + 1 < tiles!!.size && (di + 1) % puzzleWidth > 0 && tiles!![di + 1] == tiles!![di] + 1
                matchTop = di - puzzleWidth >= 0 && tiles!![di - puzzleWidth] == tiles!![di] - puzzleWidth
                matchBottom = tiles!![di] + puzzleWidth < tiles!!.size - 1 && di + puzzleWidth < tiles!!.size && tiles!![di + puzzleWidth] == tiles!![di] + puzzleWidth
            }

            if (!matchLeft) {
                sourceRect.left += FRAME_SHRINK
                targetRect.left += FRAME_SHRINK.toFloat()
            }

            if (!matchRight) {
                sourceRect.right -= FRAME_SHRINK
                targetRect.right -= FRAME_SHRINK.toFloat()
            }

            if (!matchTop) {
                sourceRect.top += FRAME_SHRINK
                targetRect.top += FRAME_SHRINK.toFloat()
            }

            if (!matchBottom) {
                sourceRect.bottom -= FRAME_SHRINK
                targetRect.bottom -= FRAME_SHRINK.toFloat()
            }

            if (isDragTile) {
                targetRect.left += dragOffsetX.toFloat()
                targetRect.right += dragOffsetX.toFloat()
                targetRect.top += dragOffsetY.toFloat()
                targetRect.bottom += dragOffsetY.toFloat()
            }

            canvas.drawBitmap(this.bitmap!!, sourceRect, targetRect, tilePaint)

            if (!matchLeft) {
                canvas.drawLine(targetRect.left, targetRect.top, targetRect.left, targetRect.bottom, framePaint)
            }

            if (!matchRight) {
                canvas.drawLine(targetRect.right - 1, targetRect.top, targetRect.right - 1, targetRect.bottom, framePaint)
            }

            if (!matchTop) {
                canvas.drawLine(targetRect.left, targetRect.top, targetRect.right, targetRect.top, framePaint)
            }

            if (!matchBottom) {
                canvas.drawLine(targetRect.left, targetRect.bottom - 1, targetRect.right, targetRect.bottom - 1, framePaint)
            }

            if (!solved && (showNumbers == ShowNumbers.ALL || showNumbers == ShowNumbers.SOME && di != tiles!![di])) {
                canvas.drawText((originalTiles[i] + 1).toString(), (targetRect.left + targetRect.right) / 2, (targetRect.top + targetRect.bottom) / 2 - (textPaint.descent() + textPaint.ascent()) / 2, textPaint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (slidePuzzle == null || this.bitmap == null) {
            return false
        }

        if (slidePuzzle!!.isSolved) {
            return false
        }

        return if (event.action == MotionEvent.ACTION_DOWN) {
            startDrag(event)
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            updateDrag(event)
        } else if (event.action == MotionEvent.ACTION_UP) {
            finishDrag(event)
        } else {
            false
        }
    }

    private fun finishDrag(event: MotionEvent): Boolean {
        if (dragging == null) {
            return false
        }

        updateDrag(event)

        if (dragInTarget) {
            doMove(dragDirection, dragging!!.size)
        } else {
            vibrate(VIBRATE_DRAG)
        }

        dragInTarget = false
        dragging = null
        invalidate()

        return true
    }

    private fun doMove(dragDirection: Int, count: Int) {
        playSlide()
        if (slidePuzzle!!.moveTile(dragDirection, count)) {
            vibrate(if (slidePuzzle!!.isSolved) VIBRATE_SOLVED else VIBRATE_MATCH)
        } else {
            vibrate(VIBRATE_DRAG)
        }

        invalidate()

        if (slidePuzzle!!.isSolved) {
            onFinish()
        }
    }

    private fun vibrate(d: Long) {
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        v?.vibrate(d)
    }

    private fun onFinish() {
        val activity = context as JuegoPuzzle
        activity.onFinish()
    }

    private fun playSlide() {
        val activity = context as JuegoPuzzle
        activity.playSound()
    }

    private fun startDrag(event: MotionEvent): Boolean {
        if (dragging != null) {
            return false
        }

        var x = (event.x.toInt() - targetOffsetX) / targetColumnWidth
        var y = (event.y.toInt() - targetOffsetY) / targetRowHeight

        if (x < 0 || x >= puzzleWidth || y < 0 || y >= puzzleHeight) {
            return false
        }

        val direction = slidePuzzle!!.getDirection(x + puzzleWidth * y)

        if (direction >= 0) {
            dragging = HashSet()

            while (x + puzzleWidth * y != slidePuzzle!!.handleLocation) {
                dragging!!.add(x + puzzleWidth * y)
                dragStartX = event.x.toInt()
                dragStartY = event.y.toInt()
                dragOffsetX = 0
                dragOffsetY = 0
                dragDirection = direction

                x -= SlidePuzzle.DIRECTION_X[direction]
                y -= SlidePuzzle.DIRECTION_Y[direction]
            }
        }

        dragInTarget = false
        vibrate(VIBRATE_DRAG)

        return true
    }

    private fun updateDrag(event: MotionEvent): Boolean {
        if (dragging == null) {
            return false
        }

        val directionX = SlidePuzzle.DIRECTION_X[dragDirection] * -1
        val directionY = SlidePuzzle.DIRECTION_Y[dragDirection] * -1

        if (directionX != 0) {
            dragOffsetX = event.x.toInt() - dragStartX

            if (Math.signum(dragOffsetX.toFloat()) != directionX.toFloat()) {
                dragOffsetX = 0
            } else if (Math.abs(dragOffsetX) > targetColumnWidth) {
                dragOffsetX = directionX * targetColumnWidth
            }
        }

        if (directionY != 0) {
            dragOffsetY = event.y.toInt() - dragStartY

            if (Math.signum(dragOffsetY.toFloat()) != directionY.toFloat()) {
                dragOffsetY = 0
            } else if (Math.abs(dragOffsetY) > targetRowHeight) {
                dragOffsetY = directionY * targetRowHeight
            }
        }

        dragInTarget = Math.abs(dragOffsetX) > targetColumnWidth / 2 || Math.abs(dragOffsetY) > targetRowHeight / 2

        invalidate()

        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val canvasWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val canvasHeight = View.MeasureSpec.getSize(heightMeasureSpec)

        if (this.canvasWidth != canvasWidth || this.canvasHeight != canvasHeight) {
            this.canvasWidth = canvasWidth
            this.canvasHeight = canvasHeight
            puzzleWidth = 0
            puzzleHeight = 0
        }
    }

    companion object {

        private val FRAME_SHRINK = 1

        private val VIBRATE_DRAG: Long = 5
        private val VIBRATE_MATCH: Long = 50
        private val VIBRATE_SOLVED: Long = 250

        private val COLOR_SOLVED = 0x00000000.toInt()
        private val COLOR_ACTIVE = 0x00000000.toInt()
    }
}
