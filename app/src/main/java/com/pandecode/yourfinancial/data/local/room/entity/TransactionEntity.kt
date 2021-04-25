package com.pandecode.yourfinancial.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandecode.yourfinancial.utils.TransactionStatus
import com.pandecode.yourfinancial.utils.TransactionType

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var amount: Int,
    var status: TransactionStatus,
    var type: TransactionType,
    var notes: String?,
    var isExpand: Boolean
)