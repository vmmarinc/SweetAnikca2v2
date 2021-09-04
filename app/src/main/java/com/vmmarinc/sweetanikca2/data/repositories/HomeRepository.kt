package com.vmmarinc.sweetanikca2.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vmmarinc.sweetanikca2.data.databases.dao.StoreDao
import com.vmmarinc.sweetanikca2.data.mock.StoreMock
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.data.models.StoreInfo
import com.vmmarinc.sweetanikca2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.sql.CommonDataSource

class HomeRepository (private val dataSource: StoreDao, private val firebaseDataSource: FirebaseFirestore){
    private val db: CollectionReference = firebaseDataSource.collection(Constants.STORE_COLECTION)

    suspend fun loadStoreInfo(): StoreInfo {
      /*  return withContext(Dispatchers.IO){
            dataSource.selectStore()!!
        }*/
        val snapshot = db.get().await()
        return snapshot.toObjects(StoreInfo::class.java)[0]
    }

    suspend fun insertStore(store: StoreInfo){
        withContext(Dispatchers.IO){
            val temp = dataSource.selectStore()
            if(temp == null){
                dataSource.insertStore(store)
            }
        }

    }

}