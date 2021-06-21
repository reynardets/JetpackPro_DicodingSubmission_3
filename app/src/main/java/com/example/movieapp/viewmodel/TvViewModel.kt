package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieapp.data.source.DataRepository
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.vo.Resources

class TvViewModel(private val DataRepository: DataRepository) : ViewModel() {
    fun getTv(): LiveData<List<TvEntity>> = DataRepository.getTv()
    fun detailTv(id: String): LiveData<TvEntity> = DataRepository.getTvDetail(id)
    fun insertTv(tv: List<TvEntity>) {
        DataRepository.insertTv(tv)
    }

    val getFavTv: LiveData<Resources<List<TvEntity>>> = DataRepository.getTvFav()
    val getTvPage: LiveData<Resources<PagedList<TvEntity>>> = DataRepository.getTvToPaged()
    fun setFavTv() {
        getFavTvDetail.value?.data?.let {
            val newState = !it.favorite
            DataRepository.setTvFav(it, newState)
        }
    }

    val tvId = MutableLiveData<Int>()
    fun setTvId(movieId: Int) {
        this.tvId.value = movieId
    }

    val getFavTvDetail: LiveData<Resources<TvEntity>> = Transformations.switchMap(tvId) {
        DataRepository.getTvFavDetail(it)
    }
}