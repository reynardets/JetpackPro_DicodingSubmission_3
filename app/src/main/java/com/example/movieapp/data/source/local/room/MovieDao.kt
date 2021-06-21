package com.example.movieapp.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity

@Dao
interface MovieDao {

    //For Movie
    @Transaction
    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>): LongArray

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMovie(movie: MovieEntity): Int

    @WorkerThread
    @Query("SELECT * FROM movie_entity WHERE favorite = 1")
    fun getMovieFav(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE favorite = 1")
    fun getMovieFavtoPaged(): DataSource.Factory<Int, MovieEntity>

    //For Tv
    @Transaction
    @Query("SELECT * FROM tv_entity WHERE id = :id")
    fun getTvById(id: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>): LongArray

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTv(tv: TvEntity): Int

    @WorkerThread
    @Query("SELECT * FROM tv_entity WHERE favorite = 1")
    fun getTvFav(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM tv_entity WHERE favorite = 1")
    fun getTvFavtoPaged(): DataSource.Factory<Int, TvEntity>
}