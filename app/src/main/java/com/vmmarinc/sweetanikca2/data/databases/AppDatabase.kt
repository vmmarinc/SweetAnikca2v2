package com.vmmarinc.sweetanikca2.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vmmarinc.sweetanikca2.data.databases.dao.CommentDao
import com.vmmarinc.sweetanikca2.data.databases.dao.ProductDao
import com.vmmarinc.sweetanikca2.data.databases.dao.StoreDao
import com.vmmarinc.sweetanikca2.data.models.Comment
import com.vmmarinc.sweetanikca2.data.models.Product
import com.vmmarinc.sweetanikca2.data.models.StoreInfo
import java.security.AccessControlContext

@Database (entities = [Comment::class, Product::class, StoreInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun commentDao(): CommentDao
    abstract fun productDao(): ProductDao
    abstract fun storeDao(): StoreDao

    companion object{
        @Volatile
        private var instace: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if(instace==null){
                synchronized(this){
                    instace = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "sweetanikca.db").build()
                }
            }
            return instace!!
        }
    }

}