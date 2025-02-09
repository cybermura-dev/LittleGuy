package ru.takeshiko.littleguy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.takeshiko.littleguy.data.dao.SleepRecordDao
import ru.takeshiko.littleguy.data.dao.StepRecordDao
import ru.takeshiko.littleguy.data.dao.UserDao
import ru.takeshiko.littleguy.data.dao.WaterRecordDao

/**
 * Main application database using Room persistence library.
 * Contains tables for user data and health metrics.
 *
 * <p>Implements singleton pattern with double-checked locking.</p>
 *
 * @see User User profile storage
 * @see WaterRecord Water consumption history
 * @see StepRecord Physical activity tracking
 * @see SleepRecord Sleep duration logs
 */
@Database(entities = [User::class, SleepRecord::class, WaterRecord::class, StepRecord::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sleepRecordDao(): SleepRecordDao
    abstract fun waterRecordDao(): WaterRecordDao
    abstract fun stepRecordDao(): StepRecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Gets singleton database instance
         * @param context Application context
         * @return Thread-safe database instance
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "littleguy_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}