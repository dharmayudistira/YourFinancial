package com.pandecode.yourfinancial.data

import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

interface IRepository {
    suspend fun getAllTransaction() : MutableList<TransactionEntity>
    suspend fun insertTransaction(transaction: TransactionEntity)
}