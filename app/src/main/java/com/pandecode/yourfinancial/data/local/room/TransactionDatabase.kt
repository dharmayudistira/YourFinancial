package com.pandecode.yourfinancial.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pandecode.yourfinancial.data.local.room.converter.Converters
import com.pandecode.yourfinancial.data.local.room.dao.TransactionDao
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

/*
    This is Room Database class that hold all the entities
    and it's converter
 */
@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}