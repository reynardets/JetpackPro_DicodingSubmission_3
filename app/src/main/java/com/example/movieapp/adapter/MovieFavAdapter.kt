package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.ui.activity.DetailActivity

class MovieFavAdapter(private val flag: Int) :
    PagedListAdapter<MovieEntity, MovieFavAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("MOVIEFAV", item!!.title.toString())
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding, flag)
    }

    class ViewHolder(private val binding: ItemMovieBinding, private val flag: Int) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: MovieEntity) {
            with(binding) {
                tvMovieTitle.text = item.title
                tvItemDate.text = item.releaseDate
                tvScore.text = ((item.voteAverage?.times(10))?.toInt()).toString() + "%"
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + item.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, item.id.toString())
                    intent.putExtra(DetailActivity.FLAG, flag)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}