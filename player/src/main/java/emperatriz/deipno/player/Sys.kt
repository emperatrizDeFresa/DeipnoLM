package emperatriz.deipno.player

import android.content.Context
import android.graphics.*
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.view.MotionEvent
import android.view.animation.Animation


object Sys {
    val prefName="deipnoPlayer"

    public val WHITE=1
    public val BROWN=2
    public val GRAY=3
    public val NAVY=4
    public val GOLDEN=5
    public val VIOLET=6
    public val BLACK=7
    public val GREEN=8
    public val MUERTO=9
    public val INVESTIGADOR=10

    public val PISTA_NO_DESCUBIERTA=0
    public val PISTA_CON_INDICIO=1
    public val PISTA_DESCUBIERTA=2

    fun checkClave(clave : String, ctx : Context) : Boolean{
        when (clave.toUpperCase()){
            "misterio".toUpperCase() -> setVeladaPersonaje(1, WHITE,ctx)
            "miedo".toUpperCase() -> setVeladaPersonaje(1, BROWN,ctx)
            "abismo".toUpperCase() -> setVeladaPersonaje(1, GRAY,ctx)
            "horror".toUpperCase() -> setVeladaPersonaje(1, NAVY,ctx)
            "paranormal".toUpperCase() -> setVeladaPersonaje(1, GOLDEN,ctx)
            "terror".toUpperCase() -> setVeladaPersonaje(1, VIOLET,ctx)
            "aterradoras".toUpperCase() -> setVeladaPersonaje(1, BLACK,ctx)
            "oculta".toUpperCase() -> setVeladaPersonaje(1, GREEN,ctx)
            "pánico".toUpperCase() -> setVeladaPersonaje(1, INVESTIGADOR,ctx)

            "pistolas".toUpperCase() -> setVeladaPersonaje(2, WHITE,ctx)
            "armas".toUpperCase() -> setVeladaPersonaje(2, BROWN,ctx)
            "rifles".toUpperCase() -> setVeladaPersonaje(2, GRAY,ctx)
            "fuego".toUpperCase() -> setVeladaPersonaje(2, NAVY,ctx)
            "revolver".toUpperCase() -> setVeladaPersonaje(2, GOLDEN,ctx)
            "escopeta".toUpperCase() -> setVeladaPersonaje(2, VIOLET,ctx)
            "beretta".toUpperCase() -> setVeladaPersonaje(2, BLACK,ctx)
            "fusil".toUpperCase() -> setVeladaPersonaje(2, GREEN,ctx)
            "disparo".toUpperCase() -> setVeladaPersonaje(2, INVESTIGADOR,ctx)

            "ejecución".toUpperCase() -> setVeladaPersonaje(3, WHITE,ctx)
            "robo".toUpperCase() -> setVeladaPersonaje(3, BROWN,ctx)
            "motosierra".toUpperCase() -> setVeladaPersonaje(3, GRAY,ctx)
            "asesinado".toUpperCase() -> setVeladaPersonaje(3, NAVY,ctx)
            "motosierra".toUpperCase() -> setVeladaPersonaje(3, GOLDEN,ctx)
            "violencia".toUpperCase() -> setVeladaPersonaje(3, VIOLET,ctx)
            "sicarios".toUpperCase() -> setVeladaPersonaje(3, BLACK,ctx)
            "policías".toUpperCase() -> setVeladaPersonaje(3, GREEN,ctx)
            "soldado".toUpperCase() -> setVeladaPersonaje(3, INVESTIGADOR,ctx)

            "familia".toUpperCase() -> setVeladaPersonaje(4, WHITE,ctx)
            "pelayo".toUpperCase() -> setVeladaPersonaje(4, BROWN,ctx)
            "niños".toUpperCase() -> setVeladaPersonaje(4, GRAY,ctx)
            "cuentos".toUpperCase() -> setVeladaPersonaje(4, NAVY,ctx)
            "desarrollo".toUpperCase() -> setVeladaPersonaje(4, GOLDEN,ctx)
            "miembros".toUpperCase() -> setVeladaPersonaje(4, VIOLET,ctx)
            "infantiles".toUpperCase() -> setVeladaPersonaje(4, BLACK,ctx)
            "clases".toUpperCase() -> setVeladaPersonaje(4, GREEN,ctx)
            "maestro".toUpperCase() -> setVeladaPersonaje(4, INVESTIGADOR,ctx)

            "crimen".toUpperCase() -> setVeladaPersonaje(5, WHITE,ctx)
            "cuerpos".toUpperCase() -> setVeladaPersonaje(5, BROWN,ctx)
            "asesino".toUpperCase() -> setVeladaPersonaje(5, GRAY,ctx)
            "homicidios".toUpperCase() -> setVeladaPersonaje(5, NAVY,ctx)
            "muerte".toUpperCase() -> setVeladaPersonaje(5, GOLDEN,ctx)
            "metralleta".toUpperCase() -> setVeladaPersonaje(5, VIOLET,ctx)
            "calibre".toUpperCase() -> setVeladaPersonaje(5, BLACK,ctx)
            "cuchillo".toUpperCase() -> setVeladaPersonaje(5, GREEN,ctx)
            "condena".toUpperCase() -> setVeladaPersonaje(5, INVESTIGADOR,ctx)

            "investigacion".toUpperCase() -> setVeladaPersonaje(6, WHITE,ctx)
            "policial".toUpperCase() -> setVeladaPersonaje(6, BROWN,ctx)
            "penal".toUpperCase() -> setVeladaPersonaje(6, GRAY,ctx)
            "pruebas".toUpperCase() -> setVeladaPersonaje(6, NAVY,ctx)
            "seguridad".toUpperCase() -> setVeladaPersonaje(6, GOLDEN,ctx)
            "delitos".toUpperCase() -> setVeladaPersonaje(6, VIOLET,ctx)
            "estado".toUpperCase() -> setVeladaPersonaje(6, BLACK,ctx)
            "procesal".toUpperCase() -> setVeladaPersonaje(6, GREEN,ctx)
            "juicio".toUpperCase() -> setVeladaPersonaje(6, INVESTIGADOR,ctx)

            "criminales".toUpperCase() -> setVeladaPersonaje(7, WHITE,ctx)
            "demente".toUpperCase() -> setVeladaPersonaje(7, BROWN,ctx)
            "casos".toUpperCase() -> setVeladaPersonaje(7, GRAY,ctx)
            "detective".toUpperCase() -> setVeladaPersonaje(7, NAVY,ctx)
            "mente".toUpperCase() -> setVeladaPersonaje(7, GOLDEN,ctx)
            "explotar".toUpperCase() -> setVeladaPersonaje(7, VIOLET,ctx)
            "ladrones".toUpperCase() -> setVeladaPersonaje(7, BLACK,ctx)
            "merecido".toUpperCase() -> setVeladaPersonaje(7, GREEN,ctx)
            "loco".toUpperCase() -> setVeladaPersonaje(7, INVESTIGADOR,ctx)

            "metodos".toUpperCase() -> setVeladaPersonaje(8, WHITE,ctx)
            "tecnicas".toUpperCase() -> setVeladaPersonaje(8, BROWN,ctx)
            "castigos".toUpperCase() -> setVeladaPersonaje(8, GRAY,ctx)
            "blasfemia".toUpperCase() -> setVeladaPersonaje(8, NAVY,ctx)
            "toro".toUpperCase() -> setVeladaPersonaje(8, GOLDEN,ctx)
            "desgarrador".toUpperCase() -> setVeladaPersonaje(8, VIOLET,ctx)
            "escalofriantes".toUpperCase() -> setVeladaPersonaje(8, BLACK,ctx)
            "cruel".toUpperCase() -> setVeladaPersonaje(8, GREEN,ctx)
            "resentido".toUpperCase() -> setVeladaPersonaje(8, INVESTIGADOR,ctx)
            else -> return false
        }
        return true
    }

    private fun setVeladaPersonaje(velada: Int, personaje: Int, context: Context){
        val prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        prefs.edit().putInt("velada", velada).apply();
        prefs.edit().putInt("personaje", personaje).apply();
    }

    fun setPistaState(peronaje:Int, estado:Int, ctx : Context){
        val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        prefs.edit().putInt("pistaDe"+peronaje, estado).apply();
    }

    fun getPistaState(peronaje:Int, ctx : Context):Int{
        val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getInt("pistaDe"+peronaje, PISTA_NO_DESCUBIERTA)
    }

    fun setVeladaIniciada(iniciada:Boolean, ctx : Context){
        val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        prefs.edit().putBoolean("velaldaIniciada", iniciada).apply();
    }

    fun isVeladaIniciada(ctx : Context):Boolean{
        val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getBoolean("velaldaIniciada", false)
    }

    fun confirmPersonajeMapa(peronaje:Int, ctx : Context){
        val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        var c = prefs.getInt("pistaMapaDe"+peronaje, 0)
        prefs.edit().putInt("pistaMapaDe"+peronaje, ++c).apply();
    }

    fun getPersonajeMapa(peronaje:Int, ctx : Context):Int{
        val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getInt("pistaMapaDe"+peronaje, 0)
    }

    fun getVelada(context: Context) : Int{
        val prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getInt("velada",-1)
    }

    fun getPersonaje(context: Context) : Int{
        val prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getInt("personaje",-1)
    }

    fun getFont(context : Context) : Typeface{
        return Typeface.createFromAsset(context.assets, "fonts/Frenchpress.otf")
    }

    fun getHandwrittenFont(context : Context) : Typeface{
        return Typeface.createFromAsset(context.assets, "fonts/Frenchpress.otf")
    }

    fun setPersonajeTitulo(txt : TextView, context : Context){
        when (getPersonaje(context)){
            WHITE -> txt.setText(R.string.tituloPersonajeWhite)
            BROWN -> txt.setText(R.string.tituloPersonajeBrown)
            GRAY -> txt.setText(R.string.tituloPersonajeGray)
            NAVY -> txt.setText(R.string.tituloPersonajeNavy)
            GOLDEN -> txt.setText(R.string.tituloPersonajeGolden)
            VIOLET -> txt.setText(R.string.tituloPersonajeViolet)
            BLACK -> txt.setText(R.string.tituloPersonajeBlack)
            GREEN -> txt.setText(R.string.tituloPersonajeGreen)
            INVESTIGADOR -> txt.setText(R.string.tituloPersonajeInvestigador)
        }
    }
    fun setPersonajeDescripcion(txt : TextView, context : Context){
        when (getPersonaje(context)){
            WHITE -> txt.setText(R.string.textoPersonajeWhite)
            BROWN -> txt.setText(R.string.textoPersonajeBrown)
            GRAY -> txt.setText(R.string.textoPersonajeGray)
            NAVY -> txt.setText(R.string.textoPersonajeNavy)
            GOLDEN -> txt.setText(R.string.textoPersonajeGolden)
            VIOLET -> txt.setText(R.string.textoPersonajeViolet)
            BLACK -> txt.setText(R.string.textoPersonajeBlack)
            GREEN -> txt.setText(R.string.textoPersonajeGreen)
            INVESTIGADOR -> txt.setText(R.string.textoPersonajeInvestigador)
        }
    }
    fun setPersonajeIndumentaria(txt : TextView, context : Context){
        when (getPersonaje(context)){
            WHITE -> txt.setText(R.string.indumentariaPersonajeWhite)
            BROWN -> txt.setText(R.string.indumentariaPersonajeBrown)
            GRAY -> txt.setText(R.string.indumentariaPersonajeGray)
            NAVY -> txt.setText(R.string.indumentariaPersonajeNavy)
            GOLDEN -> txt.setText(R.string.indumentariaPersonajeGolden)
            VIOLET -> txt.setText(R.string.indumentariaPersonajeViolet)
            BLACK -> txt.setText(R.string.indumentariaPersonajeBlack)
            GREEN -> txt.setText(R.string.indumentariaPersonajeGreen)
            INVESTIGADOR -> txt.setText(R.string.indumentariaPersonajeInvestigador)
        }
    }
    fun setPersonajeComportamiento(txt : TextView, context : Context){
        when (getPersonaje(context)){
            WHITE -> txt.setText(R.string.comportamientoPersonajeWhite)
            BROWN -> txt.setText(R.string.comportamientoPersonajeBrown)
            GRAY -> txt.setText(R.string.comportamientoPersonajeGray)
            NAVY -> txt.setText(R.string.comportamientoPersonajeNavy)
            GOLDEN -> txt.setText(R.string.comportamientoPersonajeGolden)
            VIOLET -> txt.setText(R.string.comportamientoPersonajeViolet)
            BLACK -> txt.setText(R.string.comportamientoPersonajeBlack)
            GREEN -> txt.setText(R.string.comportamientoPersonajeGreen)
            INVESTIGADOR -> txt.setText(R.string.comportamientoPersonajeInvestigador)
        }
    }
    fun setPersonajeTarea(txt : TextView, context : Context){
        when (getPersonaje(context)){
            WHITE -> txt.setText(R.string.tareaPersonajeWhite)
            BROWN -> txt.setText(R.string.tareaPersonajeBrown)
            GRAY -> txt.setText(R.string.tareaPersonajeGray)
            NAVY -> txt.setText(R.string.tareaPersonajeNavy)
            GOLDEN -> txt.setText(R.string.tareaPersonajeGolden)
            VIOLET -> txt.setText(R.string.tareaPersonajeViolet)
            BLACK -> txt.setText(R.string.tareaPersonajeBlack)
            GREEN -> txt.setText(R.string.tareaPersonajeGreen)
            INVESTIGADOR -> txt.setText(R.string.tareaPersonajeInvestigador)
        }
    }
    fun setPersonajeFoto(txt : ImageView, context : Context){
        when (getPersonaje(context)){
            WHITE -> txt.setImageResource(R.drawable.white)
            BROWN -> txt.setImageResource(R.drawable.brown)
            GRAY -> txt.setImageResource(R.drawable.grey)
            NAVY -> txt.setImageResource(R.drawable.navy)
            GOLDEN -> txt.setImageResource(R.drawable.golden)
            VIOLET -> txt.setImageResource(R.drawable.violet)
            BLACK -> txt.setImageResource(R.drawable.black)
            INVESTIGADOR -> txt.setImageResource(R.drawable.investigador)
        }
    }


    fun pistaVisible(personaje:Int, ctx : Context):Boolean{
        return getPistaState(personaje,ctx)== PISTA_DESCUBIERTA
    }

    fun getPista(personaje:Int, context:Context):String{
        when(getVelada(context)){
            1-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada1)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada1)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada1)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada1)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada1)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada1)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada1)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada1)
            }
            2-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada2)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada2)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada2)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada2)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada2)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada2)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada2)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada2)
            }
            3-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada3)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada3)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada3)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada3)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada3)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada3)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada3)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada3)
            }
            4-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada4)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada4)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada4)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada4)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada4)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada4)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada4)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada4)
            }
            5-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada5)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada5)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada5)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada5)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada5)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada5)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada5)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada5)
            }
            6-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada6)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada6)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada6)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada6)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada6)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada6)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada6)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada6)
            }
            7-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada7)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada7)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada7)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada7)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada7)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada7)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada7)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada7)
            }
            8-> when (personaje){
                WHITE -> return context.getString(R.string.pistaPersonajeWhiteVelada8)
                BROWN -> return context.getString(R.string.pistaPersonajeBrownVelada8)
                GRAY -> return context.getString(R.string.pistaPersonajeGrayVelada8)
                NAVY -> return context.getString(R.string.pistaPersonajeNavyVelada8)
                GOLDEN -> return context.getString(R.string.pistaPersonajeGoldenVelada8)
                VIOLET -> return context.getString(R.string.pistaPersonajeVioletVelada8)
                BLACK -> return context.getString(R.string.pistaPersonajeBlackVelada8)
                GREEN -> return context.getString(R.string.pistaPersonajeGreenVelada8)
            }
        }
        return " _ "
    }

    fun setPista(v : TextView, personaje : Int, context:Context){
//        if (personaje== getPersonaje(context)){
//            v.visibility= View.GONE
//        }else{
            if (pistaVisible(personaje, context)){
                when(personaje){
                    WHITE -> v.text = context.getString(R.string.White)+" "+getPista(personaje,context)
                    BROWN  -> v.text = context.getString(R.string.Brown)+" "+getPista(personaje,context)
                    GRAY  -> v.text = context.getString(R.string.Gray)+" "+getPista(personaje,context)
                    NAVY  -> v.text = context.getString(R.string.Navy)+" "+getPista(personaje,context)
                    GOLDEN -> v.text = context.getString(R.string.Golden)+" "+getPista(personaje,context)
                    VIOLET -> v.text = context.getString(R.string.Violet)+" "+getPista(personaje,context)
                    BLACK  -> v.text = context.getString(R.string.Black)+" "+getPista(personaje,context)
                    GREEN  -> v.text = context.getString(R.string.Green)+" "+getPista(personaje,context)
                }
            }else{
                v.visibility= View.GONE
            }
//        }

    }

    fun getPersonajePista(personaje : Int, context:Context):String{

                when(personaje) {
                    WHITE -> return context.getString(R.string.pWhite)
                    BROWN -> return context.getString(R.string.pBrown)
                    GRAY -> return context.getString(R.string.pGray)
                    NAVY -> return context.getString(R.string.pNavy)
                    GOLDEN -> return context.getString(R.string.pGolden)
                    VIOLET -> return context.getString(R.string.pViolet)
                    BLACK -> return context.getString(R.string.pBlack)
                    GREEN -> return context.getString(R.string.pGreen)
                }
        return ""
    }

    fun addConfirmations(personaje:Int, context:Context){
        var confirmations=""

        when(getVelada(context)){
            1-> when (personaje){
                WHITE -> confirmations = context.getString(R.string.pistaPersonajeWhiteVelada1Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada1Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada1Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada1Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada1Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada1Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada1Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada1Mapa)
            }
            2-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada2Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada2Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada2Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada2Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada2Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada2Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada2Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada2Mapa)
            }
            3-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada3Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada3Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada3Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada3Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada3Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada3Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada3Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada3Mapa)
            }
            4-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada4Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada4Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada4Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada4Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada4Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada4Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada4Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada4Mapa)
            }
            5-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada5Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada5Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada5Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada5Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada5Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada5Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada5Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada5Mapa)
            }
            6-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada6Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada6Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada6Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada6Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada6Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada6Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada6Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada6Mapa)
            }
            7-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada7Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada7Mapa)
                GRAY -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada7Mapa)
                NAVY -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada7Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada7Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada7Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada7Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada7Mapa)
            }
            8-> when (personaje){
                WHITE -> confirmations =  context.getString(R.string.pistaPersonajeWhiteVelada8Mapa)
                BROWN -> confirmations =  context.getString(R.string.pistaPersonajeBrownVelada8Mapa)
                GRAY  -> confirmations =  context.getString(R.string.pistaPersonajeGrayVelada8Mapa)
                NAVY  -> confirmations =  context.getString(R.string.pistaPersonajeNavyVelada8Mapa)
                GOLDEN -> confirmations =  context.getString(R.string.pistaPersonajeGoldenVelada8Mapa)
                VIOLET -> confirmations =  context.getString(R.string.pistaPersonajeVioletVelada8Mapa)
                BLACK -> confirmations =  context.getString(R.string.pistaPersonajeBlackVelada8Mapa)
                GREEN -> confirmations =  context.getString(R.string.pistaPersonajeGreenVelada8Mapa)
            }
        }
        if (confirmations.contains(WHITE.toString())) confirmPersonajeMapa(WHITE,context)
        if (confirmations.contains(BROWN .toString())) confirmPersonajeMapa(BROWN ,context)
        if (confirmations.contains(GRAY  .toString())) confirmPersonajeMapa(GRAY  ,context)
        if (confirmations.contains(NAVY  .toString())) confirmPersonajeMapa(NAVY  ,context)
        if (confirmations.contains(GOLDEN.toString())) confirmPersonajeMapa(GOLDEN,context)
        if (confirmations.contains(VIOLET.toString())) confirmPersonajeMapa(VIOLET,context)
        if (confirmations.contains(BLACK .toString())) confirmPersonajeMapa(BLACK ,context)
        if (confirmations.contains(GREEN .toString())) confirmPersonajeMapa(GREEN ,context)
    }

    fun checkPista(pista:String, ctx:Context):Int{
        try{
            if (pista.startsWith("deipno_pista")){
                if (pista.equals("deipno_pista_reset")){
                    return -1
                }
                if (pista.equals("deipno_pista_start")){
                    setVeladaIniciada(true,ctx)
                    return -2
                }
                val parts = pista.split("_");
                val status = Integer.parseInt(parts[2])
                val personaje = Integer.parseInt(parts[3])
                if (getPistaState(personaje,ctx) == status-1){
                    if (getPistaState(personaje,ctx) == PISTA_CON_INDICIO && status == PISTA_DESCUBIERTA){
                        addConfirmations(personaje,ctx)

                    }
                    setPistaState(personaje,status,ctx)

                }
                else{
                    return 0
                }

                return status
            }else{
                return 0;
            }
        }catch (ex:Exception){
            return 0
        }

    }

    fun reset(context:Context){
        val prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        prefs.edit().clear().commit()
    }

    fun setTextoPista(v : TextView,  context:Context){
        val personaje = getPersonaje(context)

            when(personaje) {
                WHITE -> v.text = context.getString(R.string.textPistaPersonajeWhite)
                BROWN -> v.text = context.getString(R.string.textPistaPersonajeBrown)
                GRAY -> v.text = context.getString(R.string.textPistaPersonajeGray)
                NAVY -> v.text = context.getString(R.string.textPistaPersonajeNavy)
                GOLDEN -> v.text = context.getString(R.string.textPistaPersonajeGolden)
                VIOLET -> v.text = context.getString(R.string.textPistaPersonajeViolet)
                BLACK -> v.text = context.getString(R.string.textPistaPersonajeBlack)
                GREEN -> v.text = context.getString(R.string.textPistaPersonajeGreen)
            }
    }

    fun setImagenPista(v : ImageView,  context:Context){
        val personaje = getPersonaje(context)

        when(personaje) {
            //WHITE -> v.setImageResource(R.drawable.pistawhite)
            BROWN -> v.setImageResource(R.drawable.pistabrown)
            GRAY -> v.setImageResource(R.drawable.pistagray)
            NAVY -> v.setImageResource(R.drawable.pistanavy)
            GOLDEN -> v.setImageResource(R.drawable.pistagolden)
            VIOLET -> v.setImageResource(R.drawable.pistaviolet)
            BLACK -> v.setImageResource(R.drawable.pistablack)
            GREEN -> v.setImageResource(R.drawable.pistagreen)
        }
    }



    fun setButtonAnimation(v : ImageView, anim:Animation, unanim : Animation) {
        v.setOnTouchListener(View.OnTouchListener { v, event ->

            if ((event?.action ==  MotionEvent.ACTION_DOWN)){

                v.startAnimation(anim)
            }
            if ((event?.action ==  MotionEvent.ACTION_UP)){

                v.startAnimation(unanim)
            }

            false
        })
    }

    var canvas : Canvas? = null
    var paint : Paint? = null
    val circleRadius = 0.03f
    val circleIncrement = 0.005f

    fun posX(f:Float):Float{
        return canvas!!.width*f
    }

    fun posY(f:Float):Float{
        return canvas!!.height*f
    }

    fun getColor(personaje:Int):Int{
        when(personaje) {
            WHITE -> return 0xffffffff.toInt()
            BROWN -> return 0xff660000.toInt()
            GRAY -> return 0xffaaaaaa.toInt()
            NAVY -> return 0xff0000cc.toInt()
            GOLDEN -> return 0xffcc9900.toInt()
            VIOLET -> return 0xffcc00ff.toInt()
            BLACK -> return 0xff000000.toInt()
            GREEN -> return 0xff00cc00.toInt()
        }
        return 0
    }

    fun getOutlineColor(personaje:Int):Int{
        when(personaje) {
            WHITE -> return 0xff000000.toInt()
            BROWN -> return 0xffffffff.toInt()
            GRAY -> return 0xff000000.toInt()
            NAVY -> return 0xffffffff.toInt()
            GOLDEN -> return 0xff000000.toInt()
            VIOLET -> return 0xff000000.toInt()
            BLACK -> return 0xffffffff.toInt()
            GREEN -> return 0xff000000.toInt()
        }
        return 0
    }

    class Position(x: Float, y: Float ){
        var x=x
        var y=y
    }

    val salon1 = Position(0.4f,0.2f)
    val salon2 = Position(0.4f,0.3f)
    val recibidor1 = Position(0.8f,0.18f)
    val cocina1 = Position(0.8f,0.32f)
    val cocina2 = Position(0.8f,0.42f)
    val bano1 = Position(0.71f,0.55f)
    val bano2 = Position(0.82f,0.55f)
    val despacho1 = Position(0.35f,0.5f)
    val despacho2 = Position(0.35f,0.6f)
    val dormitorio1 = Position(0.45f,0.8f)
    val dormitorio2 = Position(0.6f,0.8f)
    val pasillo = Position(0.53f,0.54f)


    fun getPosition(personaje:Int,context:Context):Position{
        val velada = getVelada(context)
        when(velada){
            1 -> when (personaje){
                WHITE -> return dormitorio1
                BROWN -> return cocina1
                GRAY -> return recibidor1
                NAVY -> return salon1
                GOLDEN -> return salon2
                VIOLET -> return bano1
                BLACK -> return bano2
                GREEN -> return dormitorio2
                MUERTO -> return despacho1
            }
            2 -> when (personaje){
                WHITE -> return dormitorio1
                BROWN -> return dormitorio2
                GRAY -> return salon1
                NAVY -> return cocina1
                GOLDEN -> return recibidor1
                VIOLET -> return salon2
                BLACK -> return cocina2
                GREEN -> return despacho1
                MUERTO -> return bano1
            }
            3 -> when (personaje){
                WHITE -> return salon1
                BROWN -> return pasillo
                GRAY -> return cocina1
                NAVY -> return recibidor1
                GOLDEN -> return bano1
                VIOLET -> return despacho1
                BLACK -> return cocina2
                GREEN -> return salon2
                MUERTO -> return dormitorio1
            }
            4 -> when (personaje){
                WHITE -> return bano1
                BROWN -> return dormitorio1
                GRAY -> return salon1
                NAVY -> return cocina1
                GOLDEN -> return cocina2
                VIOLET -> return recibidor1
                BLACK -> return salon2
                GREEN -> return dormitorio2
                MUERTO -> return pasillo
            }
            5 -> when (personaje){
                WHITE -> return dormitorio1
                BROWN -> return bano1
                GRAY -> return despacho1
                NAVY -> return salon1
                GOLDEN -> return salon2
                VIOLET -> return dormitorio2
                BLACK -> return despacho2
                GREEN -> return recibidor1
                MUERTO -> return cocina1
            }
            6 -> when (personaje){
                WHITE -> return cocina1
                BROWN -> return despacho1
                GRAY -> return dormitorio1
                NAVY -> return salon1
                GOLDEN -> return bano1
                VIOLET -> return salon2
                BLACK -> return dormitorio2
                GREEN -> return cocina2
                MUERTO -> return recibidor1
            }
            7 -> when (personaje){
                WHITE -> return cocina1
                BROWN -> return despacho1
                GRAY -> return recibidor1
                NAVY -> return salon2
                GOLDEN -> return despacho1
                VIOLET -> return bano1
                BLACK -> return cocina2
                GREEN -> return dormitorio1
                MUERTO -> return salon1
            }
            8 -> when (personaje){
                WHITE -> return salon1
                BROWN -> return despacho1
                GRAY -> return cocina1
                NAVY -> return cocina2
                GOLDEN -> return bano1
                VIOLET -> return despacho2
                BLACK -> return recibidor1
                GREEN -> return salon2
                MUERTO -> return dormitorio1
            }
        }
        return Position(0f,0f)
    }


    fun drawPersonaje(personaje:Int, context:Context){
        val pos = getPosition(personaje,context)
        if (personaje!= MUERTO){
            val confirmed = getPersonajeMapa(personaje,context)
            if (confirmed>1){
                paint!!.color= getOutlineColor(personaje)
                canvas!!.drawCircle(Sys.posX(pos.x),Sys.posY(pos.y),Sys.posX(circleRadius+circleIncrement*3),paint)
                paint!!.color= getColor(personaje)
                canvas!!.drawCircle(Sys.posX(pos.x),Sys.posY(pos.y),Sys.posX(circleRadius+circleIncrement*2),paint)
            }
            if (confirmed>0) {
                paint!!.color = getOutlineColor(personaje)
                canvas!!.drawCircle(Sys.posX(pos.x), Sys.posY(pos.y), Sys.posX(circleRadius + circleIncrement), paint)
                paint!!.color = getColor(personaje)
                canvas!!.drawCircle(Sys.posX(pos.x), Sys.posY(pos.y), Sys.posX(circleRadius), paint)
            }
        }else{
            val silueta = BitmapFactory.decodeResource(context.getResources(), R.drawable.silueta)
//            val w = (silueta.width*0.3).toInt()
//            val h = (silueta.height*0.3).toInt()
            val w = (canvas!!.width*0.14).toInt()
            val h = (canvas!!.height*0.072).toInt()
            val r = Rect(w-w/2,h-h/2,w+w/2,h+h/2)
            r.offset(Sys.posX(pos.x).toInt()-w,Sys.posY(pos.y).toInt()-h)
            canvas!!.drawBitmap(silueta,null, r,Sys.paint)
        }

    }

    fun drawPersonajeTest(personaje:Int, pos:Position,context:Context){

        val confirmed = 2
        if (confirmed>1){
            paint!!.color= getOutlineColor(personaje)
            canvas!!.drawCircle(Sys.posX(pos.x),Sys.posY(pos.y),Sys.posX(circleRadius+circleIncrement*3),paint)
            paint!!.color= getColor(personaje)
            canvas!!.drawCircle(Sys.posX(pos.x),Sys.posY(pos.y),Sys.posX(circleRadius+circleIncrement*2),paint)
        }
        if (confirmed>0) {
            paint!!.color = getOutlineColor(personaje)
            canvas!!.drawCircle(Sys.posX(pos.x), Sys.posY(pos.y), Sys.posX(circleRadius + circleIncrement), paint)
            paint!!.color = getColor(personaje)
            canvas!!.drawCircle(Sys.posX(pos.x), Sys.posY(pos.y), Sys.posX(circleRadius), paint)
        }
    }


}