package ru.takeshiko.littleguy.data

/**
 * Data class representing a content article.
 * @property title Article header text
 * @property shortDesc Brief summary text
 * @property description Full content text
 */
data class Article(
    val title: String,
    val shortDesc: String,
    val description: String
)