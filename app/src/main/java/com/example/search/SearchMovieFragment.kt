package com.example.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchMovieFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter

    private val tmdbService = TMDBService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_movie, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        moviesRecyclerView = view.findViewById(R.id.moviesRecyclerView)

        moviesAdapter = MoviesAdapter()
        moviesRecyclerView.adapter = moviesAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                searchMovies(query)
            }
        })

        return view
    }

    private fun searchMovies(query: String) {
        tmdbService.searchMovies(query, object : Callback<MovieSearchResult> {
            override fun onResponse(
                call: Call<MovieSearchResult>,
                response: Response<MovieSearchResult>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    moviesAdapter.setMovies(movies)
                }
            }

            override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                //loi gi gi do
            }
        })
    }

    companion object {

        fun newInstance(searchQuery: String = ""): SearchMovieFragment {
            val fragment = SearchMovieFragment()
            val args = Bundle()
            args.putString("searchQuery", searchQuery)
            fragment.arguments = args
            return fragment
        }

    }
}