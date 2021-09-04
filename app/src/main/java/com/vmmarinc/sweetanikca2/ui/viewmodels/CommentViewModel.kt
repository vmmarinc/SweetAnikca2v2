package com.vmmarinc.sweetanikca2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.data.repositories.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel (private val repo: CommentRepository): ViewModel() {
    private var _comments: MutableLiveData<List<Comment>> = MutableLiveData()
    val comment: LiveData<List<Comment>> get() = _comments
    /*private var _addcomment: MutableLiveData<List<Comment>> = MutableLiveData()
    val addcomment: LiveData<List<Comment>> */

    fun loadComments(){
        viewModelScope.launch {
           _comments.postValue(repo.loadComments())

        }

    }

    /*fun addComments(){

    }*/
}