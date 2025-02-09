package ru.takeshiko.littleguy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.takeshiko.littleguy.data.repo.SleepRepository

/**
 * Factory class for creating instances of SleepViewModel.
 *
 * @property repository A repository instance that will be provided to the ViewModel.
 */
class SleepViewModelFactory(
    private val repository: SleepRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of SleepViewModel.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException If the specified ViewModel class is not assignable from SleepViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SleepViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
