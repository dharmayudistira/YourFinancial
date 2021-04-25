package com.pandecode.yourfinancial.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pandecode.yourfinancial.data.Repository
import com.pandecode.yourfinancial.utils.Resource
import kotlinx.coroutines.Dispatchers

class TransactionViewModel(private val repository: Repository) : ViewModel() {
    val listTransaction = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(repository.getAllTransaction()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message))
        }

    }
}