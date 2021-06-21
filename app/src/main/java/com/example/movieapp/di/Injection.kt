package com.example.movieapp.di


import android.content.Context
import com.example.movieapp.api.ApiConfig
import com.example.movieapp.data.source.DataRepository
import com.example.movieapp.data.source.local.LocalDataSource
import com.example.movieapp.data.source.local.room.MovieDatabase
import com.example.movieapp.data.source.remote.RemoteDataSource
import com.example.movieapp.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.instance(ApiConfig)
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}