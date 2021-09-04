package com.vmmarinc.sweetanikca2.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.vmmarinc.sweetanikca2.data.mock.CommentMock
import com.vmmarinc.sweetanikca2.data.mock.ProductMock
import com.vmmarinc.sweetanikca2.data.mock.StoreMock
import org.koin.dsl.module

val dataSourceModule = module{
    single{
        StoreMock()
    }
    single {
        CommentMock()
    }
    single {
        ProductMock()
    }
    single {
        FirebaseFirestore.getInstance()
    }
    single {
        FirebaseAuth.getInstance()
    }
    single{
        FirebaseStorage.getInstance().getReference()
    }
}