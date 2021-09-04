package com.vmmarinc.sweetanikca2.di

import com.vmmarinc.sweetanikca2.data.repositories.CommentRepository
import com.vmmarinc.sweetanikca2.data.repositories.HomeRepository
import com.vmmarinc.sweetanikca2.data.repositories.ProductRepository
import com.vmmarinc.sweetanikca2.data.repositories.UserRepository
import org.koin.dsl.module
import kotlin.math.sin

val repoModule = module{
    single{
        HomeRepository(get(), get())
    }
    single {
        CommentRepository(get(), get())
    }
    single {
        ProductRepository(get(), get())
    }
    single {
        UserRepository(get(), get())
    }
}