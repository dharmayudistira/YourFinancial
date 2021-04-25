package com.pandecode.yourfinancial.utils

import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity

object DummyTransactionGenerator {
    fun generateTransaction(): MutableList<TransactionEntity> {
        return mutableListOf<TransactionEntity>().apply {
            add(
                TransactionEntity(
                    id = 1,
                    title = "Netflix subscription",
                    amount = 5000,
                    status = TransactionStatus.LUNAS,
                    type = TransactionType.EXPANSE,
                    notes = "Membeli langganan netflix selama 1 bulan",
                    isExpand = false
                )
            )
            add(
                TransactionEntity(
                    id = 2,
                    title = "Youtube premium subscription",
                    amount = 5000,
                    status = TransactionStatus.LUNAS,
                    type = TransactionType.EXPANSE,
                    notes = "Membeli langganan youtube premium selama 1 bulan",
                    isExpand = false
                )
            )
            add(
                TransactionEntity(
                    id = 3,
                    title = "Jual donat",
                    amount = 5000,
                    status = TransactionStatus.LUNAS,
                    type = TransactionType.REVENUE,
                    notes = "Menjual donat rasa coklat ke Dharma",
                    isExpand = false
                )
            )
        }
    }
}