package com.example.movietracker.model

data class SeriesDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val firstAirDate: String?,
    val rating: Float?,
    val posterPath: String?,
    val backdropPath: String?,
    val genres: List<Genre>,
    val episodeRunTime: List<Int>?
)
