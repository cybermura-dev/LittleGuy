package ru.takeshiko.littleguy.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * A singleton class responsible for managing shared preferences.
 * Provides methods to retrieve and store data in shared preferences.
 *
 * @param context The context of the application to access SharedPreferences.
 */
class PreferencesManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "littleguy_prefs"
        private const val KEY_FIRST_LAUNCH = "is_first_launch"

        @Volatile
        private var instance: PreferencesManager? = null

        /**
         * Retrieves the singleton instance of the PreferencesManager.
         * If the instance is not created, it will be initialized.
         *
         * @param context The context to access SharedPreferences.
         * @return The singleton instance of PreferencesManager.
         */
        fun getInstance(context: Context): PreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: PreferencesManager(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }

    /**
     * Retrieves the first launch status from shared preferences.
     * Default value is `true`, indicating that it's the first launch.
     *
     * @return `true` if it is the first launch, otherwise `false`.
     */
    var isFirstLaunch: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) = sharedPreferences.edit { putBoolean(KEY_FIRST_LAUNCH, value) }
}
