package com.pandecode.yourfinancial.data.local

import com.pandecode.yourfinancial.data.local.room.dao.TransactionDao
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

class LocalDataSource(private val transactionDao: TransactionDao) {

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(transactionDao: TransactionDao) : LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(transactionDao)
            }
    }

    suspend fun getAllTransaction() = transactionDao.getAllTransaction()
    suspend fun insertTransaction(transaction: TransactionEntity) = transactionDao.insertTransaction(transaction)
}