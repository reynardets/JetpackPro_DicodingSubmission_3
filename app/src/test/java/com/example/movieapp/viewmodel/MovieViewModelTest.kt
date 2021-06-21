package com.example.movieapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapp.data.source.DataRepository
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.utils.DataDummy
import com.example.movieapp.data.utils.LiveDataTest
import com.example.movieapp.vo.Resources
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var dataRepository = Mockito.mock(DataRepository::class.java)
    private var observer = Mockito.mock(Observer::class.java)
    private var movieId: Int = 297802

    @Before
    fun setup() {
        viewModel = MovieViewModel(dataRepository)
    }

    @Test
    fun getMovie() {
        val dataDummy = DataDummy.generateDummyMovie()
        val movieList = MutableLiveData<List<MovieEntity>>()
        movieList.value = dataDummy
        Mockito.`when`(dataRepository.getMovie()).thenReturn(movieList)
        val movieEntity = viewModel.getMovie().value
        verify(dataRepository).getMovie()

        TestCase.assertNotNull(movieEntity)
        TestCase.assertEquals(3, movieEntity?.size)

        viewModel.getMovie().observeForever(observer as Observer<in List<MovieEntity>>)
        verify(observer as Observer<in List<MovieEntity>>).onChanged(dataDummy)
    }

    @Test
    fun detailMovie() {
        val dataDummy = DataDummy.generateDummyDetailMovie(movieId)
        val movieDetail = MutableLiveData<MovieEntity>()
        movieDetail.value = dataDummy
        Mockito.`when`(dataRepository.getMovieDetail(movieDetail.value!!.id.toString())).thenReturn(
            movieDetail
        )
        viewModel.detailMovie(movieDetail.value!!.id.toString())
            .observeForever(observer as Observer<in MovieEntity>)
        verify(dataRepository).getMovieDetail(movieDetail.value!!.id.toString())
        TestCase.assertNotNull(movieDetail)
        assertEquals(dataDummy?.title, movieDetail.value!!.title)
        assertEquals(dataDummy?.id, movieDetail.value!!.id)
        assertEquals(dataDummy?.overview, movieDetail.value!!.overview)
        assertEquals(dataDummy?.posterPath, movieDetail.value!!.posterPath)
        assertEquals(dataDummy?.releaseDate, movieDetail.value!!.releaseDate)
        @Suppress("DEPRECATION")
        assertEquals(dataDummy?.voteAverage, movieDetail.value!!.voteAverage)
    }

    @Test
    fun getFavMovie(){
        val movie = MutableLiveData<MovieEntity>()
        movie.value = DataDummy.generateDummyDetailFavMovie(movieId)
        Mockito.`when`(dataRepository.getMovieDetail(movieId.toString())).thenReturn(movie)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getFavMovieDetail?.observeForever(observer as Observer<in Resources<MovieEntity>>)
        val result = LiveDataTest.getValue(dataRepository.getMovieDetail(movieId.toString()))
        Assert.assertNotNull(result)
    }

}