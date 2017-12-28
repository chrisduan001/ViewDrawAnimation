package com.example.chris.viewdrawanimation

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator

/**
 * Created by Chris on 12/28/17.
 */
const val PROPERTY_RADIUS = "PROPERTY_RADIUS"
const val PROPERTY_ROTATE = "PROPERTY_ROTATE"
class CustomView: View {

    constructor(context: Context): super(context)
    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int): super(context, attributeSet, defStyle)

    private var radius = 0f
    private var rotate = 0f

    private val backgroundPaint: Paint by lazy {
        val paint = Paint()
        paint.color = Color.BLUE
        return@lazy paint
    }

    private val animator: ValueAnimator by lazy {
        ValueAnimator()
    }

    override fun onDraw(canvas: Canvas?) {
        val width = width / 2f
        val height = height / 2f

        val leftTopX = width - 300f
        val leftTopY = height - 300f

        val rightX = width + 300
        val rightY = height + 300

        //rotate from center point
        canvas?.rotate(rotate, width, height)
        canvas?.drawRoundRect(leftTopX, leftTopY, rightX, rightY, radius, radius, backgroundPaint)
    }

    fun animateView() {
        val radiusProperty = PropertyValuesHolder.ofInt(PROPERTY_RADIUS, 0, 300)
        val rotateProperty = PropertyValuesHolder.ofInt(PROPERTY_ROTATE, 0, 360)
        animator.interpolator = AccelerateInterpolator()
        animator.setValues(radiusProperty, rotateProperty)
        animator.duration = 2000
        animator.addUpdateListener {
            radius = (it.getAnimatedValue(PROPERTY_RADIUS) as Int).toFloat()
            rotate = (it.getAnimatedValue(PROPERTY_ROTATE) as Int).toFloat()
            invalidate()
        }

        animator.start()
    }

    fun reset() {
        animator.cancel()
        radius = 0f
        rotate = 0f

        invalidate()
    }
}