package com.example.movieapp.data.source.remote

import android.util.Log
import com.example.movieapp.*
import com.example.movieapp.api.ApiConfig
import com.example.movieapp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val helper: ApiConfig) {
    private val apiKey = BuildConfig.API_KEY

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun instance(helper: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMovie(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        helper.getApiService().getListMovie(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.results?.let { callback.onAllMovieReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("error", t.printStackTrace().toString())
            }
        })
    }

    fun getMovieDetail(movieId: String, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        helper.getApiService().getMovie(movieId, apiKey)
            .enqueue(object : Callback<ResultsMovieItem> {
                override fun onResponse(
                    call: Call<ResultsMovieItem>,
                    response: Response<ResultsMovieItem>
                ) {
                    response.body()?.let { callback.onAllMovieDetailReceived(it) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ResultsMovieItem>, t: Throwable) {
                    Log.d("error", t.printStackTrace().toString())
                }
            })
    }

    fun getTv(callback: LoadTvCallback) {
        EspressoIdlingResource.increment()
        helper.getApiService().getListTv(apiKey).enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                response.body()?.results?.let { callback.onAllTvReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.d("error", t.printStackTrace().toString())
            }
        })
    }

    fun getTvDetail(tvId: String, callback: LoadTvDetailCallback) {
        EspressoIdlingResource.increment()
        helper.getApiService().getTv(tvId, apiKey).enqueue(object : Callback<ResultsTvItem> {
            override fun onResponse(
                call: Call<ResultsTvItem>,
                response: Response<ResultsTvItem>
            ) {
                response.body()?.let { callback.onAllTvDetailReceived(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsTvItem>, t: Throwable) {
                Log.d("error", t.printStackTrace().toString())
            }
        })
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: List<ResultsMovieItem>)
    }

    interface LoadMovieDetailCallback {
        fun onAllMovieDetailReceived(movieItem: ResultsMovieItem)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: List<ResultsTvItem>)
    }

    interface LoadTvDetailCallback {
        fun onAllTvDetailReceived(tvItem: ResultsTvItem)
    }
}