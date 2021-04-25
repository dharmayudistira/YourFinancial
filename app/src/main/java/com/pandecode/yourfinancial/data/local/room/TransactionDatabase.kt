package com.pandecode.yourfinancial.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pandecode.yourfinancial.data.local.room.converter.Converters
import com.pandecode.yourfinancial.data.local.room.dao.TransactionDao
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TransactionDatabase : RoomDatabase(){
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: TransactionDatabase? = null

        fun getInstance(context: Context): TransactionDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "transactiondb"
                ).build()
            }
    }
}