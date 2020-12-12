package com.pitchblack.tiviplus.ui.people.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitchblack.tiviplus.data.model.People
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import kotlinx.coroutines.launch

class PeopleViewModel : ViewModel() {

    private val _listPeople = MutableLiveData<List<People>>()
    val listPeople: LiveData<List<People>>
        get() = _listPeople

    init {
        initializePeopleList()
    }

    private fun initializePeopleList() {
        viewModelScope.launch {
            try {
                val response = RestAPI.tmDbService.getPopularPeople()
                _listPeople.value = response.toDomainModel()
            } catch (e: Exception) {
                Log.e("initializePeopleList", "failed : ${e.message}")
            }
        }
    }
}