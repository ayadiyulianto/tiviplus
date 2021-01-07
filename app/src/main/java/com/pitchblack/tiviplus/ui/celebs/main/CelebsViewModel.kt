package com.pitchblack.tiviplus.ui.celebs.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitchblack.tiviplus.data.model.Celebs
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.data.repository.CelebsRepository
import kotlinx.coroutines.launch

class CelebsViewModel @ViewModelInject constructor(
    private val repository: CelebsRepository
)  : ViewModel() {

    private val _listPeople = MutableLiveData<List<Celebs>>()
    val listCelebs: LiveData<List<Celebs>>
        get() = _listPeople

    init {
        initializePeopleList()
    }

    private fun initializePeopleList() {
        viewModelScope.launch {
            try {
                val response = repository.getPopular()
                _listPeople.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializePeopleList", "failed : ${e.message}")
            }
        }
    }
}