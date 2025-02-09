package ru.takeshiko.littleguy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.takeshiko.littleguy.data.SleepRecord

/**
 * Data access operations for sleep records
 */
@Dao
interface SleepRecordDao {
    /**
     * Inserts new sleep record, replacing on conflict
     * @param sleepRecord SleepRecord object to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sleepRecord: SleepRecord)

    /**
     * Updates existing sleep record
     * @param sleepRecord Modified SleepRecord object
     */
    @Update
    suspend fun update(sleepRecord: SleepRecord)

    /**
     * Deletes specific sleep record
     * @param sleepRecord Record to remove
     */
    @Delete
    suspend fun delete(sleepRecord: SleepRecord)

    /**
     * Observes user's sleep history
     * @param userId Target user ID
     * @return Flow emitting list of records sorted by datetime (newest first)
     */
    @Query("SELECT * FROM SleepRecords WHERE UserID = :userId ORDER BY DateTime DESC")
    fun getUserSleepRecords(userId: Int): Flow<List<SleepRecord>>

    /**
     * Gets sleep hours for specific date
     * @param dateTime Date in "yyyy-MM-dd" format
     * @return Hours slept (0 if no record)
     */
    @Query("SELECT COALESCE((SELECT hours FROM SleepRecords WHERE userID = :userId AND dateTime = :dateTime LIMIT 1), 0)")
    suspend fun getTodaySleepHours(userId: Int, dateTime: String): Int

    /**
     * Finds sleep record by exact date
     * @param dateTime Date in "yyyy-MM-dd" format
     * @return SleepRecord or null if not found
     */
    @Query("SELECT * FROM SleepRecords WHERE userID = :userId AND dateTime = :dateTime LIMIT 1")
    suspend fun getRecordByUserIdAndDateTime(userId: Int, dateTime: String): SleepRecord?
}
