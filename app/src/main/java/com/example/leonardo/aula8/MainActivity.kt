package com.example.leonardo.aula8

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.ColorLong
import android.support.constraint.ConstraintLayout
import android.view.View
import java.security.AccessControlContext
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
//www.ssaurel.com/blog/learn-to-draw-an-analog-clock-on-android-with-canvas-2d-api


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout_radar =  findViewById(R.id.constraintLayout_radar) as ConstraintLayout
        val background = CanvasCustom(this)

        layout_radar.addView(background)

    }


    class CanvasCustom (context: Context): View(context){

        var widthLine = 0
        var heightLine = 0
        var numericalSpacing = 0
        var padding = 0
        var handTruncation = 0
        var radius = 0
        var started : Boolean = false


        override fun onDraw (canvas: Canvas){

            if (!started){
                startRadar()
            }
            //---------------- radar drawing  --------------------
            canvas.drawRGB(255,255,255)
            var width = getWidth()
            var height = getHeight()
            var brush1 = Paint()
            brush1.setARGB(255,255,0,0)
            brush1.setStyle(Paint.Style.STROKE)
            brush1.strokeWidth = 4.0f
            for (f in 7..27)
                canvas.drawCircle((width/2).toFloat(),(height/2).toFloat(),(f*15).toFloat(),brush1)

            //---------------------------------------------------
            //drawRadar(canvas)
            drawLineToMove(canvas)
            drawDot(canvas)
            postInvalidateDelayed(500)
            invalidate()

          /*  val brush2 = Paint()
            brush2.setARGB(255,0,0,0)
            brush2.setStyle(Paint.Style.STROKE)
            brush2.strokeWidth = 8.0f
            for (value in 1..160 ) {
                canvas.drawLine((width / 2).toFloat(), (height / 2).toFloat(),
                        (width - value) / 4.toFloat(),
                        (height + value) / 4.toFloat(), brush2)


            } */



            /*var min = Math.min(height,width)
            var padding = 50
            var radius = min /2 - padding
            var handTrucation = min / 20*/
           /* val brush2 = Paint()
            brush2.setARGB(255,0,0,0)
            brush2.setStyle(Paint.Style.STROKE)
            brush2.strokeWidth = 8.0f*/

            //canvas.drawLine((width/2).toFloat(),(height/2).toFloat(),150f,(27*15).toFloat(),brush2)
            //canvas.drawLine((width/2).toFloat(),(height/2).toFloat(),150f,150f,brush2)

            //for (p in 0..27)
            //   movingLine(30,radius,handTrucation,canvas,width,height)

            //postInvalidateDelayed(500)
            //invalidate()
        }


        /*
        fun movingLine(position: Int, radius: Int, handTruncation: Int,
                       canvas: Canvas, width: Int, height: Int){
            val angle : Double = Math.PI * position / 30 - Math.PI / 2
            var handRadius = radius - handTruncation
            val brush2 = Paint()
            brush2.setARGB(255,0,0,0)
            brush2.setStyle(Paint.Style.STROKE)
            brush2.strokeWidth = 8.0f

            canvas.drawLine((width/2).toFloat(),(height/2).toFloat(),
                    ((width/2 + Math.cos(angle)) * handRadius).toFloat(),
                    ((height/2 + Math.sin(angle) * handRadius)).toFloat(),
                    brush2)



        } */


        fun drawLineToMove( canvas: Canvas){
            var calendar: Calendar = Calendar.getInstance()
            moveLine(canvas, calendar.get(Calendar.SECOND)*6)
        }


        fun moveLine(canvas: Canvas, loc: Int){
            var angle : Double = Math.PI * loc / 30 - Math.PI / 2

            var handRadius = radius - handTruncation

            val brush2 = Paint()
            brush2.setARGB(255,0,0,0)
            brush2.setStyle(Paint.Style.STROKE)
            brush2.strokeWidth = 8.0f

            canvas.drawLine((widthLine/2).toFloat(),(heightLine / 2).toFloat(),
                    (widthLine / 2 + Math.cos(angle) * handRadius).toFloat(),
                    (heightLine/2 + Math.sin(angle) * handRadius).toFloat(),
                    brush2)



        }

        fun startRadar(){
            heightLine = getHeight()
            widthLine = getWidth()
            padding =  numericalSpacing + 50
            var min = Math.min(heightLine, widthLine)
            radius = min /2 - padding
            handTruncation = min / 20
            started = true
        }

        fun drawRadar (canvas: Canvas){
            var brush1 = Paint()
            brush1.setARGB(255,255,0,0)
            brush1.setStyle(Paint.Style.STROKE)
            brush1.strokeWidth = 4.0f

            canvas.drawCircle(widthLine/2.toFloat(), heightLine/2.toFloat(), (radius + padding-10).toFloat(),
                    brush1)

        }

        fun drawDot (canvas: Canvas) {
            var brush3 = Paint()
            brush3.setARGB(255,0,255,0)
            brush3.setStyle(Paint.Style.STROKE)
            brush3.strokeWidth = 8.0f

            canvas.drawCircle((widthLine / 2).toFloat(), (heightLine/ 3).toFloat(), 12.toFloat(), brush3)

            //canvas.drawColor(Color.CYAN)

           /* var anim : ValueAnimator =  ValueAnimator.ofFloat(0f,1f)
            anim.setDuration(300)
            anim.addUpdateListener {

                canvas.drawColor(Color.parseColor("#FFFFFF"))
            }


            anim.start()*/

        }

    }

}
