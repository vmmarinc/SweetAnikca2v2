package com.vmmarinc.sweetanikca2.di

import com.vmmarinc.sweetanikca2.data.databases.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single{ AppDatabase.getInstance(get())}
    single{get<AppDatabase>().commentDao()}
    single{get<AppDatabase>().storeDao()}
    single{get<AppDatabase>().productDao()}
}