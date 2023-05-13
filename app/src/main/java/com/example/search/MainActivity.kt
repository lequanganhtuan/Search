package com.example.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moviesFragment = SearchMovieFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, moviesFragment)
            .commit()

    }
}