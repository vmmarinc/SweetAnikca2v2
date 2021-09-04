package com.vmmarinc.sweetanikca2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.data.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private var repo: ProductRepository) : ViewModel() {
    private var _product: MutableLiveData<List<Product>> = MutableLiveData()
    val product: LiveData<List<Product>> get() = _product
    private var _detail: MutableLiveData<Product> = MutableLiveData()
    val detail: LiveData<Product> = _detail

    fun loadProducts(){
        viewModelScope.launch {
            _product.postValue(repo.loadProducts())
        }
    }

    fun selectProduct(product: Product){
        _detail.postValue(product)
    }
}