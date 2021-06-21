package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.ui.activity.DetailActivity

class TvFavAdapter(private val flag: Int) :
    PagedListAdapter<TvEntity, TvFavAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovietvBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovietvBinding, flag)
    }

    class ViewHolder(private val binding: ItemMovieBinding, private val flag: Int) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TvEntity) {
            with(binding) {
                tvMovieTitle.text = item.name
                tvItemDate.text = item.firstAirDate
                tvDescription.text = item.overview
                tvScore.text = (item.voteAverage?.times(10))?.toInt().toString() + "%"
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + item.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV, item.id.toString())
                    intent.putExtra(DetailActivity.FLAG, flag)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}