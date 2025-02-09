package ru.takeshiko.littleguy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.takeshiko.littleguy.data.repo.UserRepository

/**
 * Factory class for creating instances of UserViewModel.
 *
 * @property repository A repository instance that will be provided to the ViewModel.
 */
class UserViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of UserViewModel.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException If the specified ViewModel class is not assignable from UserViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
