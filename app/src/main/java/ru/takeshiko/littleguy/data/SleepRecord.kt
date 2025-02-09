package ru.takeshiko.littleguy.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Tracks sleep duration records.
 * @property dateTime Recording date in "yyyy-MM-dd" format
 * @property hours Sleep duration (0-24)
 */
@Entity(
    tableName = "SleepRecords",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SleepRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userID: Int,
    val dateTime: String,
    val hours: Int
)