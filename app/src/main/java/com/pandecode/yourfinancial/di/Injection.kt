package com.pandecode.yourfinancial.di

import android.content.Context
import com.pandecode.yourfinancial.data.Repository
import com.pandecode.yourfinancial.data.local.LocalDataSource
import com.pandecode.yourfinancial.data.local.room.TransactionDatabase

object Injection {
    fun provideInjection(context: Context) : Repository {
        val database = TransactionDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.transactionDao())
        return Repository.getInstance(localDataSource)
    }
}