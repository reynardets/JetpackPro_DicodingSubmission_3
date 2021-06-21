package com.example.movieapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.vo.Resources

interface MovieDataSource {
    fun getMovie(): LiveData<List<MovieEntity>>
    fun getMovieDetail(movieId: String): LiveData<MovieEntity>
    fun getTv(): LiveData<List<TvEntity>>
    fun getTvDetail(tvId: String): LiveData<TvEntity>

    //For Movie Favorite
    fun getMovieFav(): LiveData<Resources<List<MovieEntity>>>
    fun getMovieFavDetail(movieId: Int): LiveData<Resources<MovieEntity>>
    fun setMovieFav(movie: MovieEntity, Fav: Boolean)
    fun insertMovie(movie: List<MovieEntity>)
    fun getMovieToPaged(): LiveData<Resources<PagedList<MovieEntity>>>

    //For Tv Favorite
    fun getTvFav(): LiveData<Resources<List<TvEntity>>>
    fun getTvFavDetail(tvId: Int): LiveData<Resources<TvEntity>>
    fun setTvFav(tv: TvEntity, Fav: Boolean)
    fun insertTv(tv: List<TvEntity>)
    fun getTvToPaged(): LiveData<Resources<PagedList<TvEntity>>>
}