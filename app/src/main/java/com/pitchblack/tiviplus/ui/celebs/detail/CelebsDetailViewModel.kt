package com.pitchblack.tiviplus.ui.celebs.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.pitchblack.tiviplus.data.model.Filmography
import com.pitchblack.tiviplus.data.model.Image
import com.pitchblack.tiviplus.data.model.CelebsDetail
import com.pitchblack.tiviplus.data.network.dto.toDomainModel
import com.pitchblack.tiviplus.data.repository.CelebsRepository
import kotlinx.coroutines.launch

class CelebsDetailViewModel @ViewModelInject constructor(
    private val repository: CelebsRepository,
    @Assisted private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val peopleId = stateHandle.get<Int>("peopleId")

    private var _peopleDetail = MutableLiveData<CelebsDetail>()
    val celebsDetail : LiveData<CelebsDetail>
        get() = _peopleDetail

    private var _filmography = MutableLiveData<List<Filmography>>()
    val filmography : LiveData<List<Filmography>>
        get() = _filmography

    private var _profileImages = MutableLiveData<List<Image>>()
    val profileImages : LiveData<List<Image>>
        get() = _profileImages

    init {
        peopleId?.let { initializePeople(it) }
    }

    private fun initializePeople(id : Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDetail(id)
                _peopleDetail.value = response.toDomainModel()
            } catch (e : Exception) {
                Log.e("initializePeople", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getFilmography(id)
                _filmography.value = response.toDomainModel()
            } catch (e : Exception) {
                Log.e("getFilm", "FAILED! ${e.message}")
            }

            try {
                val response = repository.getProfileImages(id)
                _profileImages.value = response.toDomainModel()
            } catch (e : Exception) {
                Log.e("getImages", "FAILED! ${e.message}")
            }
        }
    }
}