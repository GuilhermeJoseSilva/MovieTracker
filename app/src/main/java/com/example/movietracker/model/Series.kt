package com.example.movietracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class Series(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tmdbId: Int,
    val title: String,
    val overview: String,
    val firstAirDate: String?,
    val rating: Float?,
    val posterPath: String?,
    val isFavorite: Boolean = true
)
