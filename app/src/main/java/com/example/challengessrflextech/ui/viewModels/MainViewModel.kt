package com.example.challengessrflextech.ui.viewModels

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengessrflextech.data.models.Cat
import com.example.challengessrflextech.di.repository.MainRepositoryContract
import com.example.challengessrflextech.ui.view.DetailsActivity
import com.example.challengessrflextech.utils.Constants
import kotlinx.coroutines.launch

class MainViewModel(
    private val context:Context,
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

    fun goToDetails(cat: Cat){
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_BUNDLE_MAIN_TO_DETAILS, cat)
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(Constants.KEY_BUNDLE_MAIN_TO_DETAILS, bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }


}