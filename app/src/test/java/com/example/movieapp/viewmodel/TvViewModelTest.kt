package com.example.movieapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapp.data.source.DataRepository
import com.example.movieapp.data.source.local.entity.MovieEntity
import com.example.movieapp.data.source.local.entity.TvEntity
import com.example.movieapp.data.utils.DataDummy
import com.example.movieapp.data.utils.LiveDataTest
import com.example.movieapp.vo.Resources
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var dataRepository = Mockito.mock(DataRepository::class.java)
    private var observer = Mockito.mock(Observer::class.java)
    private var tvId: Int = 46261

    @Before
    fun setup() {
        viewModel = TvViewModel(dataRepository)
    }

    @Test
    fun getTv() {
        val dataDummy = DataDummy.generateDummyTv()
        val tvList = MutableLiveData<List<TvEntity>>()
        tvList.value = dataDummy
        Mockito.`when`(dataRepository.getTv()).thenReturn(tvList)
        val tvEntity = viewModel.getTv().value
        Mockito.verify(dataRepository).getTv()

        assertNotNull(tvEntity)
        assertEquals(3, tvEntity?.size)

        viewModel.getTv().observeForever(observer as Observer<in List<TvEntity>>)
        Mockito.verify(observer as Observer<in List<TvEntity>>).onChanged(dataDummy)
    }

    @Test
    fun detailTv() {
        val dataDummy = DataDummy.generateDummyDetailTv(tvId)
        val tvDetail = MutableLiveData<TvEntity>()
        tvDetail.value = dataDummy
        Mockito.`when`(dataRepository.getTvDetail(tvDetail.value!!.id.toString()))
            .thenReturn(tvDetail)
        viewModel.detailTv(tvDetail.value!!.id.toString())
            .observeForever(observer as Observer<in TvEntity>)
        Mockito.verify(dataRepository).getTvDetail(tvDetail.value!!.id.toString())
        assertNotNull(tvDetail)
        assertEquals(dataDummy?.name, tvDetail.value!!.name)
        assertEquals(dataDummy?.id, tvDetail.value!!.id)
        assertEquals(dataDummy?.overview, tvDetail.value!!.overview)
        assertEquals(dataDummy?.posterPath, tvDetail.value!!.posterPath)
        assertEquals(dataDummy?.firstAirDate, tvDetail.value!!.firstAirDate)
        assertEquals(dataDummy?.voteAverage, tvDetail.value!!.voteAverage)
    }

    @Test
    fun getFavTv(){
        val tv = MutableLiveData<TvEntity>()
        tv.value = DataDummy.generateDummyDetailFavTv(tvId)
        Mockito.`when`(dataRepository.getTvDetail(tvId.toString())).thenReturn(tv)
        val observer = Mockito.mock(Observer::class.java)
        viewModel.getFavTvDetail.observeForever(observer as Observer<in Resources<TvEntity>>)
        val result = LiveDataTest.getValue(dataRepository.getTvDetail(tvId.toString()))
        Assert.assertNotNull(result)
    }
}