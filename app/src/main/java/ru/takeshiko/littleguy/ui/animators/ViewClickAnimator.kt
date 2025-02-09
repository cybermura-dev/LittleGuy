package ru.takeshiko.littleguy.ui.animators

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

/**
 * Utility class to handle view click animations.
 * Provides an animation effect that scales the view when clicked.
 */
class ViewClickAnimator {

    /**
     * Animates a view with a scaling effect. The view will scale down and then return to its original size.
     * The animation duration and interpolator can be customized.
     *
     * @param view The view to animate.
     * @param scaleX The scale factor for the X-axis (default is 0.9f).
     * @param scaleY The scale factor for the Y-axis (default is 0.9f).
     * @param duration The duration of the animation in milliseconds (default is 200ms).
     * @param interpolator The interpolator to be used for the animation (default is FastOutSlowInInterpolator).
     * @param onAnimationEnd A lambda function that will be called when the animation ends (optional).
     */
    fun animate(
        view: View,
        scaleX: Float = 0.9f,
        scaleY: Float = 0.9f,
        duration: Long = 200,
        interpolator: TimeInterpolator = FastOutSlowInInterpolator(),
        onAnimationEnd: (() -> Unit)? = null
    ) {
        // Create an AnimatorSet to combine the scaleX and scaleY animations.
        val animatorSet = AnimatorSet().apply {
            playTogether(
                // Animate the scaleX and scaleY properties.
                ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scaleX, 1.0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scaleY, 1.0f)
            )
            this.duration = duration
            this.interpolator = interpolator
        }

        // Add an AnimatorListener to invoke the callback when the animation ends.
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                // Trigger the callback function after the animation ends, if provided.
                onAnimationEnd?.invoke()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })

        // Start the animation.
        animatorSet.start()
    }
}