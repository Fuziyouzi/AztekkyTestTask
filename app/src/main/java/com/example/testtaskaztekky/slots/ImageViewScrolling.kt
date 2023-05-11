package com.example.testtaskaztekky.slots

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.testtaskaztekky.R

class ImageViewScrolling : FrameLayout {
    internal lateinit var eventEnd: IEventEnd

    lateinit var nextImage: ImageView
    lateinit var currentImage: ImageView

    val value: Int
        get () = Integer.parseInt(nextImage.tag.toString())

    internal var last_result = 0
    internal var oldValue = 0

    companion object {
        private val ANIMATION_DUR = 150
    }

    fun setEventEnd(eventEnd: IEventEnd) {
        this.eventEnd = eventEnd
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.slot_frame, this)
        nextImage = findViewById(R.id.first_slot)
        currentImage = findViewById(R.id.zero_slot)

        nextImage.translationY = height.toFloat()
    }

    fun setValueRandom(image: Int, numRotate: Int) {
        currentImage.animate()
            .translationY((-height).toFloat())
            .setDuration(ANIMATION_DUR.toLong()).start()
        nextImage.translationY = nextImage.height.toFloat()

        nextImage.animate().translationY(0f).setDuration(ANIMATION_DUR.toLong())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator) {
                }

                override fun onAnimationEnd(p0: Animator) {
                    setImage(currentImage, oldValue%8)
                    currentImage.translationY = 0f
                    if (oldValue != numRotate) {
                        setValueRandom(image, numRotate)
                        oldValue++
                    } else {
                        last_result = 0
                        oldValue = 0
                        eventEnd.eventEnd(image%8, numRotate)
                    }
                }

                override fun onAnimationCancel(p0: Animator) {

                }

                override fun onAnimationRepeat(p0: Animator) {

                }

            }).start()
    }

    private fun setImage(currentImage: ImageView?, i: Int) {

        when (i) {
            Util.zero -> currentImage?.setImageResource(R.drawable.zero_symbol)
            Util.first -> currentImage?.setImageResource(R.drawable.first_symbol)
            Util.second -> currentImage?.setImageResource(R.drawable.second_symbol)
            Util.third -> currentImage?.setImageResource(R.drawable.third_symbol)
            Util.fourth -> currentImage?.setImageResource(R.drawable.fourth_symbol)
            Util.five -> currentImage?.setImageResource(R.drawable.five_symbol)
            Util.six -> currentImage?.setImageResource(R.drawable.six_symbol)
            Util.seven -> currentImage?.setImageResource(R.drawable.seven_symbol)
            Util.eight -> currentImage?.setImageResource(R.drawable.eight_symbol)
        }
        currentImage?.tag = i
        last_result = i
    }


}