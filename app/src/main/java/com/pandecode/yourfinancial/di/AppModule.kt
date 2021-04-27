package com.pandecode.yourfinancial.di

import androidx.room.Room
import com.pandecode.yourfinancial.data.Repository
import com.pandecode.yourfinancial.data.local.LocalDataSource
import com.pandecode.yourfinancial.data.local.room.TransactionDatabase
import com.pandecode.yourfinancial.ui.transaction.TransactionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
    This is modules class to handle all the modules which will be injected
 */
val databaseModule = module {
    single { get<TransactionDatabase>().transactionDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TransactionDatabase::class.java,
            "transactiondb"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { Repository(get()) }
}

val viewModelModule = module {
    viewModel {
        TransactionViewModel(get())
    }
}