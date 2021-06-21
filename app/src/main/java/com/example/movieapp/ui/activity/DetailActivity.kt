package com.example.movieapp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.databinding.ActivityDetailBinding
import com.example.movieapp.ui.fragment.MovieFragment.Companion.MOVIEFLAG
import com.example.movieapp.ui.fragment.TvFragment.Companion.TVFLAG
import com.example.movieapp.viewmodel.MovieViewModel
import com.example.movieapp.viewmodel.TvViewModel
import com.example.movieapp.viewmodel.ViewModelFactory
import com.example.movieapp.vo.Status

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movieId"
        const val EXTRA_TV = "tvId"
        const val FLAG = "flag"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private var menu: Menu? = null

    private val movieViewModel: MovieViewModel by lazy {
        val factory = ViewModelFactory.getInstance(application)
        ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    private val tvViewModel: TvViewModel by lazy {
        val factory = ViewModelFactory.getInstance(application)
        ViewModelProvider(this, factory)[TvViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        activityDetailBinding.rating.stepSize = 0.01f
        when (intent.getIntExtra(FLAG, 0)) {
            MOVIEFLAG -> {
                movieViewModel.detailMovie(intent.getStringExtra(EXTRA_MOVIE).toString())
                    .observe(this, {
                        setMovieDetail(it)
                        intent.getStringExtra(EXTRA_MOVIE)?.let { it1 ->
                            movieViewModel.setMovieId(
                                it1.toInt()
                            )
                        }
                    })
            }
            TVFLAG -> {
                tvViewModel.detailTv(intent.getStringExtra(EXTRA_TV).toString()).observe(this, {
                    setTvDetail(it)
                    intent.getStringExtra(EXTRA_TV)?.let { it1 ->
                        tvViewModel.setTvId(
                            it1.toInt()
                        )
                    }
                })
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setMovieDetail(item: MovieEntity) {
        val scoree = (item.voteAverage?.times(10))?.toFloat()?.div(20)
        activityDetailBinding.apply {
            title.text = item.title
            year.text = item.releaseDate
            rating.rating = scoree!!
            overview.text = item.overview
            score.text = ((item.voteAverage.times(10)).toInt()).toString()

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + item.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(bgPic)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + item.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(activityDetailBinding.imgPoster)
        }
    }

    private fun setTvDetail(item: TvEntity) {
        val scoree = (item.voteAverage?.times(10))?.toFloat()?.div(20)
        activityDetailBinding.apply {
            title.text = item.name
            year.text = item.firstAirDate
            rating.rating = scoree!!
            overview.text = item.overview
            score.text = ((item.voteAverage * 10).toInt()).toString()

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + item.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(bgPic)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + item.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(activityDetailBinding.imgPoster)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        this.menu = menu
        when (intent.getIntExtra(FLAG, 0)) {
            MOVIEFLAG -> {
                movieViewModel.getFavMovieDetail.observe(
                    this, { response ->
                        response?.let {
                            when (response.status) {
                                Status.LOADING -> {
                                }
                                Status.SUCCESS -> {
                                    response.data?.let {
                                        val state = it.favorite
                                        setFavorite(state)
                                    }
                                }
                                Status.ERROR -> {

                                }
                            }
                        }
                    })
            }
            TVFLAG -> {
                tvViewModel.getFavTvDetail.observe(this, { response ->
                    response?.let {
                        when (response.status) {
                            Status.LOADING -> {
                            }
                            Status.SUCCESS -> {
                                response.data?.let {
                                    val state = it.favorite
                                    setFavorite(state)
                                }
                            }
                            Status.ERROR -> {
                            }
                        }
                    }

                })
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            when (intent.getIntExtra(FLAG, 0)) {
                MOVIEFLAG -> {
                    movieViewModel.setFavMovie()
                }
                TVFLAG -> {
                    tvViewModel.setFavTv()
                }
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }
}