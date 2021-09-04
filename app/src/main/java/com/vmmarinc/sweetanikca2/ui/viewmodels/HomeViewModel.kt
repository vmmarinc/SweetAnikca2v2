package com.vmmarinc.sweetanikca2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmmarinc.sweetanikca2.data.models.StoreInfo
import com.vmmarinc.sweetanikca2.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private var _info: MutableLiveData<StoreInfo> = MutableLiveData()
    val info: LiveData<StoreInfo> get() = _info

    fun loadInfo(){
        viewModelScope.launch {
            val result = repo.loadStoreInfo()
            _info.postValue(result)
        }
    }

}