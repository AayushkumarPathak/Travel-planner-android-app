package com.techmagnet.travelplanner

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat

class OnboardingScreen : AppCompatActivity() {

    private lateinit var btnExplore: LinearLayout
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding_screen)

        btnExplore = findViewById(R.id.btn_explore)

        // Set up gesture detector
        gestureDetector = GestureDetectorCompat(this, SwipeGestureListener())

        // Attach touch listener to the swipe button
        btnExplore.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    // Gesture Listener for swipe detection
    inner class SwipeGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 != null && e2 != null) {
                val deltaX = e2.x - e1.x
                val deltaY = e2.y - e1.y

                // Check if horizontal swipe
                if (Math.abs(deltaX) > Math.abs(deltaY) && deltaX > 100) {
                    // Swiped Right
                    startSwipeAnimation()
                    return true
                }
            }
            return false
        }
    }

    private fun startSwipeAnimation() {
        val fadeOut = AlphaAnimation(1f, 0f).apply {
            duration = 500
            fillAfter = true
        }

        btnExplore.startAnimation(fadeOut)

        fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                startActivity(Intent(this@OnboardingScreen, HomeActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }
}
