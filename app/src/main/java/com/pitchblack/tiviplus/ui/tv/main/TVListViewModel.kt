package com.pitchblack.tiviplus.ui.tv.main

import android.util.Log
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.TV
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import kotlinx.coroutines.launch
import java.lang.Exception

class TVListViewModel(tabId: Int) : ViewModel() {

    private val _listTV = MutableLiveData<List<TV>>()
    val listTV: LiveData<List<TV>>
        get() = _listTV

    init {
        when(tabId) {
            TAB_TITLES[0] -> initializeTVList("popular")
            TAB_TITLES[1] -> initializeTVList("top_rated")
            TAB_TITLES[2] -> initializeTVList("upcoming")
            TAB_TITLES[3] -> initializeTVList("now_playing")
        }
    }

    private fun initializeTVList(type: String) {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getTVList(type)
                _listTV.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTVList", "failed : ${e.message}")
            }
        }
    }

    class Factory(private val tabId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(TVListViewModel::class.java)) {
                return TVListViewModel(tabId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}