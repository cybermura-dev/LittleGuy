package ru.takeshiko.littleguy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.takeshiko.littleguy.data.User
import ru.takeshiko.littleguy.data.repo.UserRepository

/**
 * ViewModel class that handles user-related data operations.
 *
 * @property repository A repository instance that handles data operations related to user data.
 */
class UserViewModel(private val repository: UserRepository) : ViewModel() {

    /**
     * Checks if the user exists in the database, or creates a new user if not found.
     *
     * @param userId The unique identifier for the user to be checked or created.
     * @param defaultUser The default User object to be created if the user does not exist.
     * @param onComplete A callback that is invoked once the user is found or created.
     */
    fun checkOrCreateUser(userId: Int, defaultUser: User, onComplete: (User) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserById(userId)
            if (user == null) {
                repository.insertUser(defaultUser)
                onComplete(defaultUser)
            } else {
                onComplete(user)
            }
        }
    }

    /**
     * Updates the user data in the database.
     *
     * @param user The user object containing updated data.
     */
    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }
}
