package com.example.movieapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.TvFavAdapter
import com.example.movieapp.databinding.FragmentTvFavBinding
import com.example.movieapp.ui.fragment.TvFragment.Companion.TVFLAG
import com.example.movieapp.viewmodel.TvViewModel
import com.example.movieapp.viewmodel.ViewModelFactory
import com.example.movieapp.vo.Status

class TvFavFragment : Fragment() {

    private lateinit var binding: FragmentTvFavBinding

    private val viewModel: TvViewModel by lazy {
        val factory = activity?.let { ViewModelFactory.getInstance(it.application) }
        ViewModelProvider(this, factory!!)[TvViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvAdapter = TvFavAdapter(TVFLAG)
            viewModel.getTvPage.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            tvAdapter.submitList(it.data)
                            tvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                        }
                    }
                }
            })
            with(binding.rvFavoriteTv) {
                layoutManager = LinearLayoutManager(context)
                adapter = tvAdapter
            }
        }
    }
}