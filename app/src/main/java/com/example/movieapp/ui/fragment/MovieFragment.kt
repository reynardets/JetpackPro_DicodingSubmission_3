package com.example.movieapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.viewmodel.MovieViewModel
import com.example.movieapp.viewmodel.ViewModelFactory
import com.example.movieapp.vo.Status

class MovieFragment : Fragment() {

    companion object {
        const val MOVIEFLAG = 1
    }

    private lateinit var binding: FragmentMovieBinding
    private var movieList = listOf<MovieEntity>()
    private val viewModel: MovieViewModel by lazy {
        val factory = activity?.let { ViewModelFactory.getInstance(it.application) }
        ViewModelProvider(this, factory!!)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter(MOVIEFLAG)
            viewModel.getMovie().observe(viewLifecycleOwner, {
                movieList = it
                movieAdapter.setItem(movieList)
                binding.rvUserMovie.visibility = View.VISIBLE
                getMovies()
                Log.d("CCD","BERHASIl")
            })

            with(binding.rvUserMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun getMovies() {
        viewModel.getFavMovie.observe(viewLifecycleOwner, {

            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data.isNullOrEmpty()) {
                        viewModel.insertMovie(movieList)
                    }
                }
                Status.ERROR -> Log.d("MovieFrag", "Error")
                Status.LOADING -> {
                }
            }
        })
    }

}