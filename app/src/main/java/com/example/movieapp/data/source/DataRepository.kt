@file:Suppress("DEPRECATION")

package com.example.movieapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieapp.MovieResponse
import com.example.movieapp.ResultsMovieItem
import com.example.movieapp.ResultsTvItem
import com.example.movieapp.data.source.local.LocalDataSource
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.data.source.remote.RemoteDataSource
import com.example.movieapp.data.source.remote.response.ApiResponse
import com.example.movieapp.utils.AppExecutors
import com.example.movieapp.vo.Resources

class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getMovie(): LiveData<List<MovieEntity>> {
        val listMovie = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<ResultsMovieItem>) {
                val movie = ArrayList<MovieEntity>()
                for (i in movieResponse.indices) {
                    val response = movieResponse[i]
                    val item = MovieEntity(
                        id = response.id,
                        title = response.title,
                        posterPath = response.posterPath,
                        releaseDate = response.releaseDate,
                        overview = response.overview,
                        voteAverage = response.voteAverage
                    )
                    movie.add(item)
                }
                listMovie.postValue(movie)
            }
        })
        return listMovie
    }

    override fun getMovieDetail(movieId: String): LiveData<MovieEntity> {
        val movieDetail = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onAllMovieDetailReceived(movieItem: ResultsMovieItem) {
                val movie = MovieEntity(
                    id = movieItem.id,
                    title = movieItem.title,
                    posterPath = movieItem.posterPath,
                    releaseDate = movieItem.releaseDate,
                    overview = movieItem.overview,
                    voteAverage = movieItem.voteAverage
                )
                movieDetail.postValue(movie)
            }
        })
        return movieDetail
    }

    override fun getTv(): LiveData<List<TvEntity>> {
        val listTv = MutableLiveData<List<TvEntity>>()
        remoteDataSource.getTv(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvResponse: List<ResultsTvItem>) {
                val tv = ArrayList<TvEntity>()
                for (i in tvResponse.indices) {
                    val response = tvResponse[i]
                    val item = TvEntity(
                        id = response.id,
                        name = response.name,
                        posterPath = response.posterPath,
                        firstAirDate = response.firstAirDate,
                        overview = response.overview,
                        voteAverage = response.voteAverage
                    )
                    tv.add(item)
                }
                listTv.postValue(tv)
            }
        })
        return listTv
    }

    override fun getTvDetail(tvId: String): LiveData<TvEntity> {
        val tvDetail = MutableLiveData<TvEntity>()
        remoteDataSource.getTvDetail(tvId, object : RemoteDataSource.LoadTvDetailCallback {
            override fun onAllTvDetailReceived(tvItem: ResultsTvItem) {
                val tv = TvEntity(
                    id = tvItem.id,
                    name = tvItem.name,
                    posterPath = tvItem.posterPath,
                    firstAirDate = tvItem.firstAirDate,
                    overview = tvItem.overview,
                    voteAverage = tvItem.voteAverage
                )
                tvDetail.postValue(tv)
            }
        })
        return tvDetail
    }

    override fun getMovieFav(): LiveData<Resources<List<MovieEntity>>> {
        return object :
            NetworkBoundResource<List<MovieEntity>, List<ResultsMovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getMovie()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovieItem>>>? {
                return null
            }

            override fun saveCallResult(data: List<ResultsMovieItem>) {

            }
        }.asLiveData()
    }

    override fun getMovieFavDetail(movieId: Int): LiveData<Resources<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, ResultsMovieItem>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMoviebyId(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<ResultsMovieItem>>? {
                return null
            }

            override fun saveCallResult(data: ResultsMovieItem) {
            }

        }.asLiveData()
    }

    override fun setMovieFav(movie: MovieEntity, Fav: Boolean) {
        val runnable = {
            localDataSource.setFavMovie(movie, Fav)
        }
        appExecutors.diskIo().execute(runnable)
    }

    override fun insertMovie(movie: List<MovieEntity>) {
        val runnable = {
            if (localDataSource.getMovie().value.isNullOrEmpty()) {
                localDataSource.insertMovie(movie)
            }
        }
        appExecutors.diskIo().execute(runnable)
    }

    override fun getMovieToPaged(): LiveData<Resources<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(
                    localDataSource.getMovieToPaged(),
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieResponse>) {
            }

        }.asLiveData()
    }

    override fun getTvFav(): LiveData<Resources<List<TvEntity>>> {
        return object : NetworkBoundResource<List<TvEntity>, List<ResultsTvItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvEntity>> {
                return localDataSource.getTv()
            }

            override fun shouldFetch(data: List<TvEntity>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvItem>>>? {
                return null
            }

            override fun saveCallResult(data: List<ResultsTvItem>) {

            }

        }.asLiveData()
    }

    override fun getTvFavDetail(tvId: Int): LiveData<Resources<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, ResultsTvItem>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> {
                return localDataSource.getTvbyId(tvId)
            }

            override fun shouldFetch(data: TvEntity?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<ResultsTvItem>>? {
                return null
            }

            override fun saveCallResult(data: ResultsTvItem) {

            }

        }.asLiveData()
    }

    override fun setTvFav(tv: TvEntity, Fav: Boolean) {
        val runnable = {
            localDataSource.setFavTv(tv, Fav)
        }
        appExecutors.diskIo().execute(runnable)
    }

    override fun insertTv(tv: List<TvEntity>) {
        val runnable = {
            if (localDataSource.getTv().value.isNullOrEmpty()) {
                localDataSource.insertTv(tv)
            }
        }
        appExecutors.diskIo().execute(runnable)
    }

    override fun getTvToPaged(): LiveData<Resources<PagedList<TvEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvEntity>, List<ResultsTvItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                return LivePagedListBuilder(
                    localDataSource.getTvToPaged(),
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvItem>>>? {
                return null
            }

            override fun saveCallResult(data: List<ResultsTvItem>) {

            }

        }.asLiveData()
    }


}