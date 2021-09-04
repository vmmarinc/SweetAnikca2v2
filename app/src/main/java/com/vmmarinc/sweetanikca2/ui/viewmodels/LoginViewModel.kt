package com.vmmarinc.sweetanikca2.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.vmmarinc.sweetanikca2.data.repositories.UserRepository
import kotlinx.coroutines.launch
import java.lang.Error

class LoginViewModel(private val repo: UserRepository): ViewModel() {
    private var _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    val user: LiveData<FirebaseUser?> get() = _user

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get()= _error

    fun signUp(email: String, name: String, pass: String){
        viewModelScope.launch {
            try {
                _user.postValue(repo.signup(email, name, pass))
            } catch (e: Error){
                _error.postValue(e.message!!)
            }
        }
    }

    fun login(email: String, pass: String){
        viewModelScope.launch {
            try {
                _user.postValue(repo.login(email, pass))
            } catch (e: Error){
                _error.postValue(e.message!!)
            }
        }
    }

    fun isLoggedIn() {
        viewModelScope.launch {
            _user.postValue(repo.isLoggedIn())
        }
    }

    fun logOut(){
        viewModelScope.launch {
            _user.postValue(repo.logOut())
        }
    }

    fun uploadimage(bitmap : Bitmap){
        viewModelScope.launch {
            _user.postValue(repo.uploadImage(bitmap))
        }
    }
}