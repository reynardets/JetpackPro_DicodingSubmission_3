package com.example.movieapp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieapp.data.source.local.LocalDataSource
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.data.source.remote.RemoteDataSource
import com.example.movieapp.data.utils.DataDummy
import com.example.movieapp.data.utils.LiveDataTest
import com.example.movieapp.utils.AppExecutors
import com.example.movieapp.vo.Resources
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val dataRepository = FakeDataRepository(remote, local, appExecutors)
    private val movieResponse = DataDummy.generateMovieResponse()
    private val tvResponse = DataDummy.generateTvResponse()
    private val movieDetail = DataDummy.generateMovieDetailResponse()
    private val tvDetail = DataDummy.generateTvDetailResponse()
    private val movieId = movieResponse[0].id
    private val tvId = tvResponse[0].id
    private fun <T> anyOfGeneric(type: Class<T>): T = Mockito.any(type)
    private fun <T> eqOfGeneric(obj: T): T = Mockito.eq(obj)

    @Test
    fun getMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java)
        Mockito.`when`(local.getMovieToPaged())
            .thenReturn(dataSourceFactory as DataSource.Factory<Int, MovieEntity>)
        dataRepository.getMovieToPaged()
        verify(local).getMovieToPaged()
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = movieId?.let { DataDummy.generateDummyDetailMovie(it) }
        Mockito.`when`(movieId?.let { local.getMoviebyId(it) }).thenReturn(movie)
        val result =
            movieId?.let { dataRepository.getMovieFavDetail(it) }?.let { LiveDataTest.getValue(it) }
        movieId?.let { verify(local).getMoviebyId(it) }
        Assert.assertNotNull(result)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java)
        Mockito.`when`(local.getTvToPaged())
            .thenReturn(dataSourceFactory as DataSource.Factory<Int, TvEntity>)
        dataRepository.getTvToPaged()
        verify(local).getTvToPaged()
    }

    @Test
    fun getTvShow() {
        val tv = MutableLiveData<TvEntity>()
        tv.value = tvId?.let { DataDummy.generateDummyDetailTv(it) }
        Mockito.`when`(tvId?.let { local.getTvbyId(it) }).thenReturn(tv)
        val result =
            tvId.let { dataRepository.getTvFavDetail(it) }?.let { LiveDataTest.getValue(it) }
        tvId.let {
            verify(local).getTvbyId(it)
        }
        Assert.assertNotNull(result)
    }

    @Test
    fun getMovieList() {
        Mockito.doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.LoadMovieCallback
            callback.onAllMovieReceived(movieResponse)
            null
        }.`when`(remote).getMovie(anyOfGeneric(RemoteDataSource.LoadMovieCallback::class.java))
        val result = LiveDataTest.getValue(dataRepository.getMovie())
        verify(
            remote,
            Mockito.times(1)
        ).getMovie(anyOfGeneric(RemoteDataSource.LoadMovieCallback::class.java))
        assertEquals(movieResponse.size, result.size)
        Assert.assertNotNull(result)
    }

    @Test
    fun getMovieDetail() {
        Mockito.doAnswer {
            val callback = it.arguments[1] as RemoteDataSource.LoadMovieDetailCallback
            movieDetail.let { it1 -> callback.onAllMovieDetailReceived(it1) }
            null
        }.`when`(remote).getMovieDetail(
            eqOfGeneric(movieId.toString()),
            anyOfGeneric(RemoteDataSource.LoadMovieDetailCallback::class.java)
        )
        val result = movieId.let { dataRepository.getMovieDetail(it.toString()) }
            .let { LiveDataTest.getValue(it) }
        verify(
            remote,
            Mockito.times(1)
        ).getMovieDetail(
            eqOfGeneric(movieId.toString()),
            anyOfGeneric(RemoteDataSource.LoadMovieDetailCallback::class.java)
        )
        assertEquals(movieDetail.id, result.id)
    }

    @Test
    fun getTvShowList() {
        Mockito.doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.LoadTvCallback
            callback.onAllTvReceived(tvResponse)
            null
        }.`when`(remote)
            .getTv(anyOfGeneric(RemoteDataSource.LoadTvCallback::class.java))
        val result = LiveDataTest.getValue(dataRepository.getTv())
        Mockito.verify(
            remote,
            Mockito.times(1)
        ).getTv(anyOfGeneric(RemoteDataSource.LoadTvCallback::class.java))
        assertEquals(tvResponse.size, result.size)
    }

    @Test
    fun getTvShowById() {
        Mockito.doAnswer {
            val callback = it.arguments[1] as RemoteDataSource.LoadTvDetailCallback
            tvDetail.let { it1 -> callback.onAllTvDetailReceived(it1) }
            null
        }.`when`(remote).getTvDetail(
            eqOfGeneric(tvId.toString()),
            anyOfGeneric(RemoteDataSource.LoadTvDetailCallback::class.java)
        )
        val result = tvId.let { dataRepository.getTvDetail(it.toString()) }
            ?.let { LiveDataTest.getValue(it) }
        verify(
            remote,
            Mockito.times(1)
        ).getTvDetail(
            eqOfGeneric(tvId.toString()),
            anyOfGeneric(RemoteDataSource.LoadTvDetailCallback::class.java)
        )
        assertEquals(tvDetail.id, result.id)
    }
}