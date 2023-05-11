package com.example.challengessrflextech.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengessrflextech.data.models.Cat
import com.example.challengessrflextech.di.repository.MainRepositoryContract
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: MainRepositoryContract
):ViewModel() {

    val cats = MutableLiveData<List<Cat>>()
    fun getImagesRelated(keyWord:String?){
        viewModelScope.launch {
            if (!keyWord.isNullOrEmpty()){
                val listCats = repository.getImagesRelated(keyWord)
                if (!listCats.isNullOrEmpty()){
                    listCats.let { cats.value = it }
                }
            }
        }
    }


}