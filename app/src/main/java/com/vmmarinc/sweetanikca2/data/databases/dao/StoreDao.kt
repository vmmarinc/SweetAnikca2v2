package com.vmmarinc.sweetanikca2.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vmmarinc.sweetanikca2.data.models.StoreInfo

@Dao
interface StoreDao {
    @Insert
    suspend fun insertStore(store: StoreInfo)

    @Query("SELECT * FROM StoreInfo LIMIT 1")
    suspend fun selectStore() : StoreInfo?
}