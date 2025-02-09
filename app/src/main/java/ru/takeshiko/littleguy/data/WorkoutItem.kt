package ru.takeshiko.littleguy.data

import androidx.annotation.DrawableRes

/**
 * Contains metadata for individual workout program.
 * @property title Workout name
 * @property imageRes Drawable resource ID for preview image
 * @property category Workout category classification
 * @property link External video content URL
 */
data class WorkoutItem(
    val title: String,
    @DrawableRes val imageRes: Int,
    val category: WorkoutCategory,
    val link: String
)
