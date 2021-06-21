package com.example.movieapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieFavAdapter
import com.example.movieapp.databinding.FragmentMovieFavBinding
import com.example.movieapp.ui.fragment.MovieFragment.Companion.MOVIEFLAG
import com.example.movieapp.viewmodel.MovieViewModel
import com.example.movieapp.viewmodel.ViewModelFactory
import com.example.movieapp.vo.Status

class MovieFavFragment : Fragment() {

    private lateinit var binding: FragmentMovieFavBinding

    private val viewModel: MovieViewModel by lazy {
        val factory = activity?.let { ViewModelFactory.getInstance(it.application) }
        ViewModelProvider(this, factory!!)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieFavAdapter(MOVIEFLAG)
            viewModel.getMoviePage.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            movieAdapter.submitList(it.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                        }
                    }
                }
            })
            with(binding.rvFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }
        }
    }
}