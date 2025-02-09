package ru.takeshiko.littleguy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.takeshiko.littleguy.data.WaterRecord

/**
 * Data access operations for water consumption records.
 * Supports CRUD operations and daily aggregate calculations.
 */
@Dao
interface WaterRecordDao {
    /**
     * Inserts new water consumption record, replacing on conflict
     * @param waterRecord WaterRecord object to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(waterRecord: WaterRecord)

    /**
     * Updates existing water consumption record
     * @param waterRecord Modified WaterRecord object
     */
    @Update
    suspend fun update(waterRecord: WaterRecord)

    /**
     * Deletes specified water consumption record
     * @param waterRecord Record to remove
     */
    @Delete
    suspend fun delete(waterRecord: WaterRecord)

    /**
     * Calculates total water consumed on specified date
     * @param date Filter date in "yyyy-MM-dd" format
     * @return Total milliliters consumed that day
     */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM WaterRecords WHERE userID = :userId AND dateTime LIKE :date || '%'")
    suspend fun getTodayWaterAmount(userId: Int, date: String): Int

    /**
     * Finds record by exact timestamp
     * @param dateTime Full timestamp in "yyyy-MM-dd HH:mm" format
     * @return Matching record or null
     */
    @Query("SELECT * FROM WaterRecords WHERE userID = :userId AND dateTime = :dateTime LIMIT 1")
    suspend fun getRecordByUserIdAndDateTime(userId: Int, dateTime: String): WaterRecord?

    /**
     * Observes user's water consumption history
     * @param userId Target user ID
     * @return Flow emitting list of records sorted by datetime (newest first)
     */
    @Query("SELECT * FROM WaterRecords WHERE userID = :userId ORDER BY dateTime DESC")
    fun getUserWaterRecords(userId: Int): Flow<List<WaterRecord>>
}

