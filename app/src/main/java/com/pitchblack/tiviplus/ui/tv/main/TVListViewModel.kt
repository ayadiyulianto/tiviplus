package com.pitchblack.tiviplus.ui.tv.main

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.TV
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.data.repository.TVRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class TVListViewModel @ViewModelInject constructor(
    private val repository : TVRepository,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tabId = savedStateHandle.get<Int>("")
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
                val response = repository.getList(type)
                _listTV.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializeTVList", "failed : ${e.message}")
            }
        }
    }
}