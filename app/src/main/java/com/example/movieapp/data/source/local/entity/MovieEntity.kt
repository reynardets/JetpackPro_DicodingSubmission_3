package com.example.movieapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.load.engine.Resource

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey
    var id: Int? = 0,
    val title: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val overview: String? = null,
    val voteAverage: Double? = 0.0,
    var favorite: Boolean = false
)