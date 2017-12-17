package emperatriz.deipno.player

import android.util.Log
import java.util.*

/**
 * Created by ramon on 17/12/2017.
 */
class SlidePuzzle {

    private var tiles: IntArray? = null
    var handleLocation: Int = 0
        private set

    private val random = Random()
    var width: Int = 0
        private set
    var height: Int = 0
        private set

    val isSolved: Boolean
        get() {
            for (i in tiles!!.indices) {
                if (tiles!![i] != i) {
                    return false
                }
            }

            return true
        }

    val possibleMoves: Int
        get() {
            val x = getColumnAt(handleLocation)
            val y = getRowAt(handleLocation)

            val left = x > 0
            val right = x < width - 1
            val up = y > 0
            val down = y < height - 1

            return (if (left) 1 shl DIRECTION_LEFT else 0) or
                    (if (right) 1 shl DIRECTION_RIGHT else 0) or
                    (if (up) 1 shl DIRECTION_UP else 0) or
                    if (down) 1 shl DIRECTION_DOWN else 0
        }

    fun init(width: Int, height: Int) {
        this.width = width
        this.height = height
        tiles = IntArray(width * height)

        for (i in tiles!!.indices) {
            tiles!![i] = i
        }

        handleLocation = tiles!!.size - 1
    }

    fun setTiles(tiles: IntArray) {
        this.tiles = tiles

        for (i in tiles.indices) {
            if (tiles[i] == tiles.size - 1) {
                handleLocation = i
                break
            }
        }
    }

    fun getTiles(): IntArray? {
        return tiles // should not be written
    }

    fun getColumnAt(location: Int): Int {
        return location % width
    }

    fun getRowAt(location: Int): Int {
        return location / width
    }

    fun distance(): Int {
        var dist = 0

        for (i in tiles!!.indices) {
            dist += Math.abs(i - tiles!![i])
        }

        return dist
    }

    fun shuffle() {
        if (width < 2 || height < 2) {
            return
        }

        val limit = width * height * Math.max(width, height)
        var move = 0

        while (distance() < limit) {

            move = pickRandomMove(invertMove(move))
            moveTile(move, 1)
        }

    }

    private fun invertMove(move: Int): Int {
        if (move == 0) {
            return 0
        }

        if (move == 1 shl DIRECTION_LEFT) {
            return 1 shl DIRECTION_RIGHT
        }

        if (move == 1 shl DIRECTION_UP) {
            return 1 shl DIRECTION_DOWN
        }

        if (move == 1 shl DIRECTION_RIGHT) {
            return 1 shl DIRECTION_LEFT
        }

        if (move == 1 shl DIRECTION_DOWN) {
            return 1 shl DIRECTION_UP
        }

        return 0
    }

    private fun pickRandomMove(exclude: Int): Int {
        val moves = ArrayList<Int>(4)
        val possibleMoves = possibleMoves and exclude.inv()

        if (possibleMoves and (1 shl DIRECTION_LEFT) > 0) {
            moves.add(DIRECTION_LEFT)
        }

        if (possibleMoves and (1 shl DIRECTION_UP) > 0) {
            moves.add(DIRECTION_UP)
        }

        if (possibleMoves and (1 shl DIRECTION_RIGHT) > 0) {
            moves.add(DIRECTION_RIGHT)
        }

        if (possibleMoves and (1 shl DIRECTION_DOWN) > 0) {
            moves.add(DIRECTION_DOWN)
        }

        return moves[random.nextInt(moves.size)]
    }

    fun moveTile(direction: Int, count: Int): Boolean {
        var match = false

        for (i in 0 until count) {
            val targetLocation = handleLocation + DIRECTION_X[direction] + DIRECTION_Y[direction] * width
            tiles!![handleLocation] = tiles!![targetLocation]
            match = match or (tiles!![handleLocation] == handleLocation)
            tiles!![targetLocation] = tiles!!.size - 1 // handle tile
            handleLocation = targetLocation
        }
        return match
    }

    fun getDirection(location: Int): Int {
        val delta = location - handleLocation

        return if (delta % width == 0) {
            if (delta < 0) DIRECTION_UP else DIRECTION_DOWN
        } else if (handleLocation / width == (handleLocation + delta) / width) {
            if (delta < 0) DIRECTION_LEFT else DIRECTION_RIGHT
        } else {
            -1
        }
    }

    companion object {
        val DIRECTION_LEFT = 0
        val DIRECTION_UP = 1
        val DIRECTION_RIGHT = 2
        val DIRECTION_DOWN = 3

        val DIRECTION_X = intArrayOf(-1, 0, +1, 0)
        val DIRECTION_Y = intArrayOf(0, -1, 0, +1)
    }
}
