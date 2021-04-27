package com.pandecode.yourfinancial.data.local.room.converter

import androidx.room.TypeConverter
import com.pandecode.yourfinancial.utils.TransactionStatus
import com.pandecode.yourfinancial.utils.TransactionType

/*
    Since room can only keep primitive data
    so we need a converters to keep non-primitive data
 */
class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toTransactionStatus(value: String) = enumValueOf<TransactionStatus>(value)

        @TypeConverter
        @JvmStatic
        fun fromTransactionStatus(value: TransactionStatus) = value.name

        @TypeConverter
        @JvmStatic
        fun toTransactionType(value: String) = enumValueOf<TransactionType>(value)

        @TypeConverter
        @JvmStatic
        fun fromTransactionType(value: TransactionType) = value.name

    }

}