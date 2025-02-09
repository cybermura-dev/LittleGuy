package ru.takeshiko.littleguy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.takeshiko.littleguy.data.StepRecord

/**
 * Step count data access operations
 */
@Dao
interface StepRecordDao {
    /**
     * Inserts new step count record, replacing on conflict
     * @param stepRecord StepRecord object to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stepRecord: StepRecord)

    @Update
    suspend fun update(stepRecord: StepRecord)

    /**
     * Deletes specified step count record
     * @param stepRecord Record to remove
     */
    @Delete
    suspend fun delete(stepRecord: StepRecord)

    /**
     * Observes step history with automatic updates
     * @param userId Filter user ID
     * @return Flow emitting sorted step records
     */
    @Query("SELECT * FROM StepRecords WHERE UserID = :userId ORDER BY DateTime DESC")
    fun getUserStepRecords(userId: Int): Flow<List<StepRecord>>
}