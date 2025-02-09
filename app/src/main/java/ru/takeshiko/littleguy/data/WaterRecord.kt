package ru.takeshiko.littleguy.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Represents water consumption records in the database.
 * @property userID Foreign key linking to User table
 * @property dateTime Recording timestamp in "yyyy-MM-dd HH:mm" format
 * @property amount Milliliters of water consumed
 */
@Entity(
    tableName = "WaterRecords",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WaterRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userID: Int,
    val dateTime: String,
    val amount: Int
)