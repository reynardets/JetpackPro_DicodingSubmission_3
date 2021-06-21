package com.example.movieapp.ui.fragment

import TvAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.databinding.FragmentTvBinding
import com.example.movieapp.viewmodel.TvViewModel
import com.example.movieapp.viewmodel.ViewModelFactory
import com.example.movieapp.vo.Status

class TvFragment : Fragment() {

    companion object {
        const val TVFLAG = 2
    }

    private lateinit var binding: FragmentTvBinding
    private var tvList = listOf<TvEntity>()
    private val viewModel: TvViewModel by lazy {
        val factory = activity?.let { ViewModelFactory.getInstance(it.application) }
        ViewModelProvider(this, factory!!)[TvViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvAdapter = TvAdapter(TVFLAG)
            viewModel.getTv().observe(viewLifecycleOwner, {
                tvList = it
                tvAdapter.setItem(tvList)
                binding.rvUserTv.visibility = View.VISIBLE
                getTv()
            })

            with(binding.rvUserTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    private fun getTv() {
        viewModel.getFavTv.observe(viewLifecycleOwner, {

            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data.isNullOrEmpty()) {
                        viewModel.insertTv(tvList)
                    }
                }
                Status.ERROR -> Log.d("TvFrag", "Error")
                Status.LOADING -> {
                }
            }
        })
    }
}