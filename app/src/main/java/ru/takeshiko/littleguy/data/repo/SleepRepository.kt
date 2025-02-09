package ru.takeshiko.littleguy.data.repo

import ru.takeshiko.littleguy.data.SleepRecord
import ru.takeshiko.littleguy.data.dao.SleepRecordDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Manages sleep duration tracking operations
 * @property sleepRecordDao Data access object for sleep records
 */
class SleepRepository(private val sleepRecordDao: SleepRecordDao) {
    /**
     * Updates daily sleep record, overwriting previous entry
     * @param userId Target user ID
     * @param hours New sleep duration (0-24)
     */
    suspend fun addOrUpdateSleepRecord(userId: Int, hours: Int) {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        val existingRecord = sleepRecordDao.getRecordByUserIdAndDateTime(userId, today)
        if (existingRecord != null) {
            sleepRecordDao.update(existingRecord.copy(hours = hours))
        } else {
            sleepRecordDao.insert(SleepRecord(userID = userId, dateTime = today, hours = hours))
        }
    }

    /**
     * Retrieves current day's sleep duration
     * @param userId Target user ID
     * @return Hours slept today (0 if no record)
     */
    suspend fun getTodaySleepHours(userId: Int): Int {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return sleepRecordDao.getTodaySleepHours(userId, today)
    }

    /**
     * Observes user's sleep history
     * @param userId Target user ID
     * @return Flow emitting list of sleep records
     */
    fun getUserSleepRecords(userId: Int) = sleepRecordDao.getUserSleepRecords(userId)
}
