package com.pandecode.yourfinancial.data.local.room.dao

import androidx.room.*
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionEntity")
    suspend fun getAllTransaction() : MutableList<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

}