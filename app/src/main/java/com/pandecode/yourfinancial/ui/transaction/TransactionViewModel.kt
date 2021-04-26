package com.pandecode.yourfinancial.ui.transaction

import androidx.lifecycle.ViewModel
import com.pandecode.yourfinancial.data.Repository

class TransactionViewModel(private val repository: Repository) : ViewModel() {
    val listTransaction = repository.getAllTransaction()
}