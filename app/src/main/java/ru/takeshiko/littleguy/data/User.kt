package ru.takeshiko.littleguy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Stores user profile information.
 * @property name Optional display name (max 15 chars)
 * @property age Optional in range 1-120
 * @property weight Optional in kg (20-300)
 * @property height Optional in cm (50-250)
 * @property avatar Optional profile picture byte array
 */
@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val age: Int?,
    val weight: Float?,
    val height: Int?,
    val avatar: ByteArray?
)