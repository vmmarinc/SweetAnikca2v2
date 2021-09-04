package com.vmmarinc.sweetanikca2.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.data.databases.dao.ProductDao
import com.vmmarinc.sweetanikca2.data.mock.ProductMock
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class ProductRepository(private val dataSource: ProductDao, private val firebaseDataSource: FirebaseFirestore) {
    private val db: CollectionReference = firebaseDataSource.collection(Constants.PRODUCTS_COLECTION)

    suspend fun loadProducts(): List<Product>{
        /*return withContext(Dispatchers.IO){
            dataSource.getAllProducts()
        }*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Product::class.java)
    }

    suspend fun insertProducts(products: List<Product>){
        withContext(Dispatchers.IO){
            val temp = dataSource.getAllProducts()
            if (temp.isEmpty()){
                dataSource.insertProducts(products)
            }
        }
    }
}