package com.example.movieapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entity")
data class TvEntity(
    @PrimaryKey
    val id: Int? = 0,
    val name: String? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = 0.0,
    var favorite: Boolean = false
)