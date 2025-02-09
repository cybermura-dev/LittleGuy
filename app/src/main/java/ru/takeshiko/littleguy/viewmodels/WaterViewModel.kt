package ru.takeshiko.littleguy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.takeshiko.littleguy.data.WaterRecord
import ru.takeshiko.littleguy.data.repo.WaterRepository

/**
 * ViewModel class that manages the data for water intake and records.
 *
 * @property repository A repository instance that handles data operations related to water records.
 */
class WaterViewModel(private val repository: WaterRepository) : ViewModel() {

    private val _waterAmount = MutableLiveData<Int>()
    val waterAmount: LiveData<Int> = _waterAmount

    /**
     * Loads the total amount of water consumed for the current day for a specific user.
     *
     * @param userId The unique identifier for the user whose water intake data is to be fetched.
     */
    fun loadTodayWaterAmount(userId: Int) {
        viewModelScope.launch {
            _waterAmount.value = repository.getTodayWaterAmount(userId)
        }
    }

    /**
     * Adds a water record for a user with a fixed amount (e.g., 250 ml).
     *
     * @param userId The unique identifier for the user whose water record is to be added.
     */
    fun addWater(userId: Int) {
        viewModelScope.launch {
            repository.addWaterRecord(userId, 250)
            loadTodayWaterAmount(userId)
        }
    }

    /**
     * Retrieves the water records for a user.
     *
     * @param userId The unique identifier for the user whose water records are to be retrieved.
     * @return A LiveData object containing a list of WaterRecord objects for the specified user.
     */
    fun getUserWaterRecords(userId: Int): LiveData<List<WaterRecord>> {
        return repository.getUserWaterRecords(userId).asLiveData()
    }
}
