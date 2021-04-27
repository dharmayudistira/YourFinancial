package com.pandecode.yourfinancial.data

import androidx.lifecycle.LiveData
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

/*
    This is repository interface that hold all request function
 */
interface IRepository {
    fun getAllTransaction() : LiveData<MutableList<TransactionEntity>>
    suspend fun insertTransaction(transaction: TransactionEntity)
}