package ru.takeshiko.littleguy.data.repo

import ru.takeshiko.littleguy.data.WaterRecord
import ru.takeshiko.littleguy.data.dao.WaterRecordDao
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Handles water consumption tracking logic
 * @property waterRecordDao Data access object for water records
 */
class WaterRepository(private val waterRecordDao: WaterRecordDao) {
    /**
     * Adds or updates water consumption entry
     * @param userId Target user ID
     * @param amount Milliliters to add (positive integer)
     */
    suspend fun addWaterRecord(userId: Int, amount: Int) {
        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        val existingRecord = waterRecordDao.getRecordByUserIdAndDateTime(userId, currentDateTime)

        if (existingRecord != null) {
            val updatedAmount = existingRecord.amount + amount
            waterRecordDao.update(existingRecord.copy(amount = updatedAmount))
        } else {
            waterRecordDao.insert(WaterRecord(userID = userId, dateTime = currentDateTime, amount = amount))
        }
    }

    /**
     * Gets today's total water consumption
     * @param userId Target user ID
     * @return Total milliliters consumed today
     */
    suspend fun getTodayWaterAmount(userId: Int): Int {
        val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return waterRecordDao.getTodayWaterAmount(userId, currentDate)
    }

    /**
     * Observes water consumption history
     * @param userId Filter by user ID
     * @return Flow emitting sorted list of water records
     */
    fun getUserWaterRecords(userId: Int) = waterRecordDao.getUserWaterRecords(userId)
}

