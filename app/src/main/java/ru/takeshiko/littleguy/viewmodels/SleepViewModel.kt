package ru.takeshiko.littleguy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.takeshiko.littleguy.data.SleepRecord
import ru.takeshiko.littleguy.data.repo.SleepRepository

/**
 * ViewModel class that manages the data for sleep-related information.
 *
 * @property repository A repository instance that handles data operations related to sleep records.
 */
class SleepViewModel(private val repository: SleepRepository) : ViewModel() {

    private val _todaySleepHours = MutableLiveData<Int>()
    val todaySleepHours: LiveData<Int> = _todaySleepHours

    /**
     * Loads the total hours of sleep for the current day for a specific user.
     *
     * @param userId The unique identifier for the user whose sleep data is to be fetched.
     */
    fun loadTodaySleepHours(userId: Int) {
        viewModelScope.launch {
            _todaySleepHours.value = repository.getTodaySleepHours(userId)
        }
    }

    /**
     * Adds or updates the sleep record for a specific user with the provided sleep hours.
     *
     * @param userId The unique identifier for the user whose sleep data is to be added or updated.
     * @param hours The number of hours to be recorded.
     */
    fun addOrUpdateSleepRecord(userId: Int, hours: Int) {
        viewModelScope.launch {
            repository.addOrUpdateSleepRecord(userId, hours)
            loadTodaySleepHours(userId)
        }
    }

    /**
     * Retrieves the sleep records for a user.
     *
     * @param userId The unique identifier for the user whose sleep records are to be retrieved.
     * @return A LiveData object containing a list of SleepRecord objects for the specified user.
     */
    fun getUserSleepRecords(userId: Int): LiveData<List<SleepRecord>> {
        return repository.getUserSleepRecords(userId).asLiveData()
    }
}
