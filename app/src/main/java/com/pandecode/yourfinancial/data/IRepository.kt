package com.pandecode.yourfinancial.data

import androidx.lifecycle.LiveData
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

interface IRepository {
    fun getAllTransaction() : LiveData<MutableList<TransactionEntity>>
    suspend fun insertTransaction(transaction: TransactionEntity)
}