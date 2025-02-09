package ru.takeshiko.littleguy.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.User
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.utils.PreferencesManager
/**
 * Initial onboarding activity with a "Get Started" button.
 * Handles first launch state and navigation to main menu.
 */
class GetStartedActivity : AppCompatActivity() {
    private lateinit var appPreferences: PreferencesManager

    /**
     * Initializes UI components and sets up click listener for navigation button.
     * Uses animation for button interaction and updates first launch preference.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        appPreferences = PreferencesManager.getInstance(this)

        // Initialize button
        val btnGetStarted = findViewById<View>(R.id.btn_get_started)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        /**
         * Sets the click listener for the "Get Started" button.
         * When clicked, it navigates to the HomeActivity.
         */
        btnGetStarted.setOnClickListener {
            animator.animate(btnGetStarted, onAnimationEnd = {
                appPreferences.isFirstLaunch = false

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }
}