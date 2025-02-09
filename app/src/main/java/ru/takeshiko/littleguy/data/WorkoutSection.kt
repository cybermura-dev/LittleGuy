package ru.takeshiko.littleguy.data

/**
 * Represents a collection of workout items grouped by category.
 * @property title Section header text
 * @property items List of workout items in section
 */
data class WorkoutSection(
    val title: String,
    val items: List<WorkoutItem>
)