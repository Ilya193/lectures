package com.xlwe.lectures

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SmileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.apply {
            val centerX = (width / 2).toFloat()
            val centerY = (height / 2).toFloat()

            drawCircle(centerX, centerY, SMILE_RADIUS, paint)

            paint.style = Paint.Style.STROKE
            paint.color = Color.BLACK
            paint.strokeWidth = 10f
            drawCircle(centerX, centerY, SMILE_RADIUS + 1f, paint)

            paint.style = Paint.Style.FILL
            paint.strokeWidth = 30f
            drawCircle(centerX - 100, centerY - 100, EYE_RADIUS, paint)
            drawCircle(centerX + 100, centerY - 100, EYE_RADIUS, paint)
            drawLine(centerX - 100, centerY + 100, centerX + 100, centerY + 100, paint)
        }
    }

    companion object {
        private const val SMILE_RADIUS = 300f
        private const val EYE_RADIUS= 35f
    }
}