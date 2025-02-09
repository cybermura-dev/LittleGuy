package ru.takeshiko.littleguy.ui

import android.os.Bundle
import android.text.InputFilter
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.takeshiko.littleguy.R
import ru.takeshiko.littleguy.data.AppDatabase
import ru.takeshiko.littleguy.data.User
import ru.takeshiko.littleguy.data.repo.UserRepository
import ru.takeshiko.littleguy.ui.animators.ViewClickAnimator
import ru.takeshiko.littleguy.viewmodels.UserViewModel
import ru.takeshiko.littleguy.viewmodels.UserViewModelFactory

/**
 * User profile editing screen with input validation.
 * Handles personal data updates using ViewModel and database operations.
 */
class PersonalDataEditorActivity : AppCompatActivity() {
    /**
     * Custom input validation constraints for personal data editing.
     */
    companion object {
        /**
         * Maximum allowed characters for name input
         */
        private const val MAX_NAME_LENGTH = 15

        /**
         * Valid age range constraints
         */
        private const val MIN_AGE = 1
        private const val MAX_AGE = 120

        /**
         * Valid height range constraints (cm)
         */
        private const val MIN_HEIGHT = 50
        private const val MAX_HEIGHT = 250

        /**
         * Valid weight range constraints (kg)
         */
        private const val MIN_WEIGHT = 20f
        private const val MAX_WEIGHT = 300f
    }

    private val userRepository: UserRepository by lazy {
        val database = AppDatabase.getDatabase(this)
        UserRepository(database.userDao())
    }

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory(userRepository))[UserViewModel::class.java]
    }

    private var currentUser: User? = null

    /**
     * Initializes input fields with current user data.
     * Sets up validation rules and save button click handler.
     * @param savedInstanceState Previously saved state bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data_editor)

        val etName = findViewById<EditText>(R.id.name_field)
        val etAge = findViewById<EditText>(R.id.age_field)
        val etHeight = findViewById<EditText>(R.id.height_field)
        val etWeight = findViewById<EditText>(R.id.weight_field)
        val btnSave = findViewById<ImageView>(R.id.btn_save)

        etName.filters = arrayOf(InputFilter.LengthFilter(MAX_NAME_LENGTH))

        val animator = ViewClickAnimator()

        val defaultUser = User(
            id = 1,
            name = null,
            age = null,
            weight = null,
            height = null,
            avatar = null
        )
        userViewModel.checkOrCreateUser(1, defaultUser) { user ->
            currentUser = user
            etName.setText(user.name ?: "")
            etAge.setText(user.age?.toString() ?: "")
            etHeight.setText(user.height?.toString() ?: "")
            etWeight.setText(user.weight?.toString() ?: "")
        }

        btnSave.setOnClickListener {
            animator.animate(btnSave, onAnimationEnd = {
                val newName = etName.text.toString().ifBlank { null }
                val newAge = etAge.text.toString().toIntOrNull()
                val newHeight = etHeight.text.toString().toIntOrNull()
                val newWeight = etWeight.text.toString().toFloatOrNull()

                if (newName != null && newName.length > MAX_NAME_LENGTH) {
                    etName.error = "Имя не должно превышать $MAX_NAME_LENGTH символов"
                    return@animate
                }

                if (newAge == null || newAge !in MIN_AGE..MAX_AGE) {
                    etAge.error = "Введите корректный возраст ($MIN_AGE–$MAX_AGE лет)"
                    return@animate
                }

                if (newHeight == null || newHeight !in MIN_HEIGHT..MAX_HEIGHT) {
                    etHeight.error = "Введите корректный рост ($MIN_HEIGHT–$MAX_HEIGHT см)"
                    return@animate
                }

                if (newWeight == null || newWeight !in MIN_WEIGHT..MAX_WEIGHT) {
                    etWeight.error = "Введите корректный вес ($MIN_WEIGHT–$MAX_WEIGHT кг)"
                    return@animate
                }

                currentUser?.let { user ->
                    val updatedUser = user.copy(
                        name = newName,
                        age = newAge,
                        height = newHeight,
                        weight = newWeight
                    )
                    userViewModel.updateUser(updatedUser)
                }

                finish()
            })
        }
    }
}
