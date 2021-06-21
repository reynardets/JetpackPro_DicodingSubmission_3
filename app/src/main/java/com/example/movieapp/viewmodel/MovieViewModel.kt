package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieapp.data.source.DataRepository
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.vo.Resources

class MovieViewModel(private val DataRepository: DataRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = DataRepository.getMovie()
    fun detailMovie(id: String): LiveData<MovieEntity> = DataRepository.getMovieDetail(id)
    fun insertMovie(movie: List<MovieEntity>) {
        DataRepository.insertMovie(movie)
    }

    val getFavMovie: LiveData<Resources<List<MovieEntity>>> = DataRepository.getMovieFav()
    val getMoviePage: LiveData<Resources<PagedList<MovieEntity>>> = DataRepository.getMovieToPaged()
    fun setFavMovie() {
        getFavMovieDetail.value?.data?.let {
            val newState = !it.favorite
            DataRepository.setMovieFav(it, newState)

        }
    }

    val movieId = MutableLiveData<Int>()
    fun setMovieId(movieId: Int) {
        this.movieId.value = movieId
    }

    val getFavMovieDetail: LiveData<Resources<MovieEntity>> = Transformations.switchMap(movieId) {
        DataRepository.getMovieFavDetail(it)
    }
}