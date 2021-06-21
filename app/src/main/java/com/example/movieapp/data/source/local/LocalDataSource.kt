package com.example.movieapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getMovie(): LiveData<List<MovieEntity>> = mMovieDao.getMovieFav()

    fun getMoviebyId(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovieById(movieId)

    fun insertMovie(movieList: List<MovieEntity>) = mMovieDao.insertMovie(movieList)

    fun setFavMovie(movie: MovieEntity, Fav: Boolean) {
        movie.favorite = Fav
        mMovieDao.updateMovie(movie)
    }

    fun getMovieToPaged(): DataSource.Factory<Int, MovieEntity> {
        return mMovieDao.getMovieFavtoPaged()
    }

    fun getTv(): LiveData<List<TvEntity>> = mMovieDao.getTvFav()

    fun getTvbyId(tvId: Int): LiveData<TvEntity> = mMovieDao.getTvById(tvId)

    fun insertTv(tvList: List<TvEntity>) = mMovieDao.insertTv(tvList)

    fun setFavTv(tv: TvEntity, Fav: Boolean) {
        tv.favorite = Fav
        mMovieDao.updateTv(tv)
    }

    fun getTvToPaged(): DataSource.Factory<Int, TvEntity> {
        return mMovieDao.getTvFavtoPaged()
    }


}