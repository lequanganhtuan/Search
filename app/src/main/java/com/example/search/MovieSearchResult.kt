package com.example.search

data class MovieSearchResult(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val results: List<Movie>
)
