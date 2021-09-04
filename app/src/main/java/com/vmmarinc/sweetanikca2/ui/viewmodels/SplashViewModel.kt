package com.vmmarinc.sweetanikca2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.data.models.StoreInfo
import com.vmmarinc.sweetanikca2.data.repositories.CommentRepository
import com.vmmarinc.sweetanikca2.data.repositories.HomeRepository
import com.vmmarinc.sweetanikca2.data.repositories.ProductRepository
import com.vmmarinc.sweetanikca2.data.repositories.UserRepository
import kotlinx.coroutines.launch
//import org.w3c.dom.Comment

class SplashViewModel(private val repo: CommentRepository,
                      private val repoProduct: ProductRepository, private val repoStore: HomeRepository,
                      private val repoUser: UserRepository): ViewModel() {
    private var _user:MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?> get() = _user

    fun insert(comments: List<Comment>, products: List<Product>, store: StoreInfo){
        viewModelScope.launch {
            repo.insertComments(comments)
            repoProduct.insertProducts(products)
            repoStore.insertStore(store)
        }
    }

    fun isLoggedIn(){
        viewModelScope.launch {
            _user.postValue(repoUser.isLoggedIn())
        }

    }
}