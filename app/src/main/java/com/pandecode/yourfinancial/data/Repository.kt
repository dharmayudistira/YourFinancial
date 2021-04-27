package com.pandecode.yourfinancial.data

import com.pandecode.yourfinancial.data.local.LocalDataSource
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

/*
    This is Repository class that implements repository interface
    to help ViewModels communicate with local data source
 */
class Repository(
    private val localDataSource: LocalDataSource
) : IRepository {

    override fun getAllTransaction() = localDataSource.getAllTransaction()

    override suspend fun insertTransaction(transaction: TransactionEntity) =
        localDataSource.insertTransaction(transaction)
}