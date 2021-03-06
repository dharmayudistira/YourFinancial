package com.pandecode.yourfinancial.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

/*
    This is DAO class to handle all database requests
 */
@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionEntity")
    fun getAllTransaction(): LiveData<MutableList<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM TransactionEntity WHERE id = :id")
    suspend fun deleteTransaction(id: Int)
}