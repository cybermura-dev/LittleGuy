package ru.takeshiko.littleguy.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Contains physical activity step counts.
 * @property dateTime Recording date in "yyyy-MM-dd" format
 * @property count Number of steps recorded
 */
@Entity(
    tableName = "StepRecords",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class StepRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userID: Int,
    val dateTime: String,
    val count: Int
)
