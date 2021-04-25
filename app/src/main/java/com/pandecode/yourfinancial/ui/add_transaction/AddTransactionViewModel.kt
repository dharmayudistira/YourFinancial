package com.pandecode.yourfinancial.ui.add_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.yourfinancial.data.Repository
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import kotlinx.coroutines.launch

class AddTransactionViewModel(private val repository: Repository) : ViewModel() {
    fun insertTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        repository.insertTransaction(transaction)
    }
}