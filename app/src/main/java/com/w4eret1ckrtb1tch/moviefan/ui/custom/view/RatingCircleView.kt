package com.W4ereT1ckRtB1tch.moviefan.ui.custom.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use
import com.w4eret1ckrtb1tch.moviefan.R
import kotlin.math.min

class RatingCircleView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) :
    View(context, attributeSet) {

    private val oval = RectF()
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var stroke = 5f
    private var progress = 50
    private var background = Color.DKGRAY //цвет фона
    private var backgroundShadow = Color.BLACK //тень фона
    private var digitRatingShadow = Color.DKGRAY //цвет тени рейтинга
    private lateinit var backgroundPaint: Paint //краска для фона
    private lateinit var digitRatingPaint: Paint //краска для рейтинга
    private lateinit var circleRatingPaint: Paint //краска для кольца прогресса

    companion object {
        private const val DEFAULT_SIZE_VIEW = 300 //размер view по умолчанию
        private const val DEFAULT_SCALE = 60f //минимальный размер элемента
        private const val DEFAULT_SCALE_RADIUS = 0.8f //масштаб кольца прогресса
        private const val DEFAULT_SCALE_TEXT_SIZE = 0.75f //масштаб рейтинга
    }

    init {
        val attr =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.RatingRoundView, 0, 0)

        attr.use {
            stroke =
                it.getDimensionPixelSize(R.styleable.RatingRoundView_stroke_round, stroke.toInt())
                    .toFloat()
            progress = it.getInt(R.styleable.RatingRoundView_progress, progress)
            background = it.getColor(R.styleable.RatingRoundView_background_color, background)
            backgroundShadow =
                it.getColor(R.styleable.RatingRoundView_background_shadow_color, backgroundShadow)
            digitRatingShadow =
                it.getColor(R.styleable.RatingRoundView_digit_shadow_color, digitRatingShadow)
        }

        initPaint()
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        initPaint() //обновляем краски
        invalidate() //перерисовыем View
    }

    private fun initPaint() {

        backgroundPaint = Paint().apply {
            style = Paint.Style.FILL
            setShadowLayer(5f, 0f, 0f, backgroundShadow)
            color = background
            isAntiAlias = true
        }

        digitRatingPaint = Paint().apply {

            style = Paint.Style.FILL_AND_STROKE
            setShadowLayer(5f, 0f, 0f, digitRatingShadow)
            strokeWidth = 2f
            textSize = DEFAULT_SCALE * DEFAULT_SCALE_TEXT_SIZE
            typeface = Typeface.SANS_SERIF
            color = getColorPaint(progress)
            isAntiAlias = true
        }

        circleRatingPaint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = stroke
            color = getColorPaint(progress)
            isAntiAlias = true
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(width, height).div(2f)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val chooseWidth = getDimension(widthMode, widthSize)
        val chooseHeight = getDimension(heightMode, heightSize)
        val minSide = min(chooseWidth, chooseHeight)
        centerX = minSide.div(2f)
        centerY = minSide.div(2f)
        setMeasuredDimension(minSide, minSide)
    }

    override fun onDraw(canvas: Canvas?) {

        drawCircle(canvas) //рейтинг кольцо
        drawDigit(canvas) // рейтинг цифра
    }

    private fun drawCircle(canvas: Canvas?) {
        val centerOffset = radius * DEFAULT_SCALE_RADIUS
        oval.set(-centerOffset, -centerOffset, centerOffset, centerOffset)

        canvas?.let {
            it.save()
            it.translate(centerX, centerY)
            it.drawCircle(0f, 0f, radius, backgroundPaint)
            it.drawArc(oval, -90f, convertProgressToDegrees(progress), false, circleRatingPaint)
            it.restore()
        }
    }

    private fun drawDigit(canvas: Canvas?) {
        val text = String.format("%.1f", progress / 10f)
        drawTextCenter(canvas, text, digitRatingPaint)
    }

    private fun drawTextCenter(canvas: Canvas?, text: String, paint: Paint) {

        val rect = Rect()
        canvas?.getClipBounds(rect)
        val canvasWidth = rect.width()
        val canvasHeight = rect.height()
        paint.textAlign = Paint.Align.LEFT
        paint.getTextBounds(text, 0, text.length, rect)
        val x = canvasWidth / 2f - rect.width() / 2f - rect.left
        val y = canvasHeight / 2f + rect.height() / 2f - rect.bottom
        canvas?.drawText(text, x, y, paint)
    }

    private fun getColorPaint(progress: Int): Int = when (progress) {
        in 0..25 -> Color.parseColor("#e84258")
        in 26..50 -> Color.parseColor("#fd8060")
        in 51..75 -> Color.parseColor("#fee191")
        else -> Color.parseColor("#b0d8a4")
    }

    private fun getDimension(mode: Int, size: Int): Int {
        return when (mode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
            else -> DEFAULT_SIZE_VIEW
        }
    }

    private fun convertProgressToDegrees(progress: Int): Float {
        return progress * 3.6f
    }

}