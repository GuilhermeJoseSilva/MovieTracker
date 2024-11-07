package com.example.movietracker.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String?,
    val rating: Float?,
    val posterPath: String?,
    val backdropPath: String?,
    val genres: List<Genre>,
    val runtime: Int?
)
