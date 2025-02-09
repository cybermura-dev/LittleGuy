package ru.takeshiko.littleguy.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.AppDatabase
import ru.takeshiko.littleguy.data.User
import ru.takeshiko.littleguy.data.repo.UserRepository
import ru.takeshiko.littleguy.utils.PreferencesManager
import ru.takeshiko.littleguy.viewmodels.UserViewModel
import ru.takeshiko.littleguy.viewmodels.UserViewModelFactory

/**
 * Initial splash screen activity with delayed navigation logic.
 * Determines appropriate destination based on first launch state.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var appPreferences: PreferencesManager

    private lateinit var userRepository: UserRepository

    private lateinit var userViewModel: UserViewModel

    /**
     * Companion object that contains constant values used in the SplashActivity.
     * In this case, the splash screen delay duration.
     */
    private companion object {
        /**
         * Delay duration for the splash screen in milliseconds.
         */
        const val SPLASH_DELAY_MS = 2000L
    }

    /**
     * Displays splash screen and initiates delayed navigation.
     * Checks/Creates default user and determines destination activity.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        appPreferences = PreferencesManager.getInstance(this)

        val database = AppDatabase.getDatabase(this)
        userRepository = UserRepository(database.userDao())
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        )[UserViewModel::class.java]

        val defaultUser = User(
            id = 1,
            name = null,
            age = null,
            weight = null,
            height = null,
            avatar = null
        )
        userViewModel.checkOrCreateUser(1, defaultUser) {}

        navigateToNextActivity() // Initiates the transition to the next activity
    }

    /**
     * Performs delayed navigation after splash timeout.
     * Uses coroutine for non-blocking delay implementation.
     */
    private fun navigateToNextActivity() {
        lifecycleScope.launch {
            delay(SPLASH_DELAY_MS) // Wait for the splash screen duration
            if (!isFinishing) { // Check if the activity is not finishing before starting the next activity
                val destination = if (appPreferences.isFirstLaunch) {
                    GetStartedActivity::class.java
                } else {
                    MenuActivity::class.java
                }

                startActivity(Intent(this@SplashActivity, destination))
                finish() // Finish the current activity to prevent returning to it
            }
        }
    }
}