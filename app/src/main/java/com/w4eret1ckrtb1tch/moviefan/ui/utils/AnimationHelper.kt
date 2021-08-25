package com.W4ereT1ckRtB1tch.moviefan.ui.utils

import android.app.Activity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnStart
import java.util.concurrent.Executors
import kotlin.math.hypot
import kotlin.math.roundToInt

object AnimationHelper {

    private const val MENU_ITEMS = 4

    fun performFragmentCircularRevealAnimation(rootView: View, activity: Activity, position: Int) {
        Executors.newSingleThreadExecutor().execute {

            while (true) {
                if (rootView.isAttachedToWindow) {
                    activity.runOnUiThread {

                        val itemCenter = rootView.width / (MENU_ITEMS * 2)
                        val step = (itemCenter * 2) * (position - 1) + itemCenter
                        val x: Int = step
                        val y: Int = rootView.y.roundToInt() + rootView.height
                        val startRadius = 0f
                        val endRadius = hypot(rootView.width.toFloat(), rootView.height.toFloat())
                        val animation = ViewAnimationUtils.createCircularReveal(
                            rootView,
                            x,
                            y,
                            startRadius,
                            endRadius
                        ).apply {
                            duration = 500
                            interpolator = AccelerateDecelerateInterpolator()
                            doOnStart {
                                rootView.visibility = View.VISIBLE
                            }
                        }
                        animation.start()
                    }
                    return@execute
                }
            }
        }
    }
}