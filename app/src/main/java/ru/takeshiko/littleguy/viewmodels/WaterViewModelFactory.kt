package ru.takeshiko.littleguy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.takeshiko.littleguy.data.repo.WaterRepository

/**
 * Factory class for creating instances of WaterViewModel.
 *
 * @property repository A repository instance that will be provided to the ViewModel.
 */
class WaterViewModelFactory(
    private val repository: WaterRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of WaterViewModel.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException If the specified ViewModel class is not assignable from WaterViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WaterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WaterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
