package com.pandecode.yourfinancial.data

import com.pandecode.yourfinancial.data.local.LocalDataSource
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

class Repository private constructor(
    private val localDataSource: LocalDataSource
) : IRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(localDataSource: LocalDataSource) : Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(localDataSource)
            }
    }

    override suspend fun getAllTransaction(): MutableList<TransactionEntity> = localDataSource.getAllTransaction()

    override suspend fun insertTransaction(transaction: TransactionEntity) = localDataSource.insertTransaction(transaction)
}