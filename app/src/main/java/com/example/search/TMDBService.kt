package com.example.search

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TMDBService {
    private val apiKey = "eb82a323e426d30d552550d47bc83e2b"
    private val apiBaseUrl = "https://api.themoviedb.org/3/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(TMDBApi::class.java)

    fun searchMovies(query: String, callback: Any) {
        api.searchMovies(apiKey, query).enqueue(callback as Callback<MovieSearchResult>)
    }
}