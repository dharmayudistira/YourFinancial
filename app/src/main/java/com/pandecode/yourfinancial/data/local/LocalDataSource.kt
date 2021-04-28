package com.pandecode.yourfinancial.data.local

import com.pandecode.yourfinancial.data.local.room.dao.TransactionDao
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

/*
    This is local data source class that have a responsibility
    to execute all database request from repository
 */
class LocalDataSource(private val transactionDao: TransactionDao) {

    fun getAllTransaction() = transactionDao.getAllTransaction()
    suspend fun insertTransaction(transaction: TransactionEntity) =
        transactionDao.insertTransaction(transaction)
    suspend fun deleteTransaction(id: Int) = transactionDao.deleteTransaction(id)
}