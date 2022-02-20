package com.xlwe.lectures

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var radius = 0.0f
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private var currValue = 0
    private var _isEnabled = false

    private val numbers = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = 10f
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    fun start() {
        _isEnabled = true
        Thread {
            while (_isEnabled) {
                Thread.sleep(1000)
                if (_isEnabled)
                    invalidate()
            }
        }.start()
    }

    fun stop() {
        _isEnabled = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.GREEN

        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(currValue++, markerRadius)
        if (currValue == 12)
            currValue = 0
        paint.color = Color.BLACK
        canvas.drawLine(width / 2f, height / 2f, pointPosition.x, pointPosition.y, paint)

        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in numbers.indices) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = i.toString()
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    private fun PointF.computeXYForSpeed(pos: Int, radius: Float) {
        val startAngle = -Math.PI / 2
        val angle = startAngle + pos * (Math.PI / 6)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }
}