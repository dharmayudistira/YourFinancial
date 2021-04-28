package com.pandecode.yourfinancial.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.yourfinancial.data.Repository
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: Repository) : ViewModel() {
    val listTransaction = repository.getAllTransaction()
    fun insertTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        repository.insertTransaction(transaction)
    }
    fun deleteTransaction(id: Int) = viewModelScope.launch {
        repository.deleteTransaction(id)
    }
}