package com.pandecode.yourfinancial.ui.add_transaction

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pandecode.yourfinancial.R
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import com.pandecode.yourfinancial.databinding.BottomSheetAddTransactionBinding
import com.pandecode.yourfinancial.ui.transaction.TransactionViewModel
import com.pandecode.yourfinancial.utils.TransactionStatus
import com.pandecode.yourfinancial.utils.TransactionType
import com.pandecode.yourfinancial.utils.ViewModelFactory

class AddTransactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetAddTransactionBinding? = null
    private val binding get() = _binding as BottomSheetAddTransactionBinding

    private lateinit var viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()

        binding.btnSaveAddTransaction.setOnClickListener {
            if (validateInput()) {
                addTransaction()
            }
        }

    }

    private fun addTransaction() {
        val amount = binding.edtInputAmountAddTransaction.editText?.text.toString().toDouble()
        val title = binding.edtInputTitleAddTransaction.editText?.text.toString()
        val type = getTransactionType()
        val status = getTransactionStatus()
        val notes = binding.edtInputNotesAddTransaction.editText?.text?.toString()

        val transactionEntity = TransactionEntity(
            title = title,
            amount = amount,
            status = status,
            type = type,
            notes = notes,
            isExpand = false
        )

        viewModel.insertTransaction(transactionEntity)

        dismiss()
    }

    private fun getTransactionStatus(): TransactionStatus {
        return when (binding.rgStatusAddTransaction.checkedRadioButtonId) {
            R.id.rb_lunas_add_transaction -> {
                TransactionStatus.LUNAS
            }
            R.id.rb_belum_lunas_add_transaction -> {
                TransactionStatus.BELUM_LUNAS
            }
            else -> {
                TransactionStatus.BELUM_LUNAS // returning belum lunas status as it is default value
            }
        }
    }

    private fun getTransactionType(): TransactionType {
        return when (binding.rgTypeAddTransaction.checkedRadioButtonId) {
            R.id.rb_revenue_add_transaction -> {
                TransactionType.REVENUE
            }
            R.id.rb_expanse_add_transaction -> {
                TransactionType.EXPANSE
            }
            else -> {
                TransactionType.EXPANSE // returning expanse type as it is default value
            }
        }
    }

    private fun validateInput(): Boolean {
        val inputAmount = binding.edtInputAmountAddTransaction.editText
        val inputTitle = binding.edtInputTitleAddTransaction.editText

        if (TextUtils.isEmpty(inputAmount?.text)) {
            inputAmount?.error = resources.getString(R.string.error_empty_input_amount)
            inputAmount?.requestFocus()
            return false
        }

        if (TextUtils.isEmpty(inputTitle?.text)) {
            inputTitle?.error = resources.getString(R.string.error_empty_input_title)
            inputTitle?.requestFocus()
            return false
        }

        return true
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(activity as AppCompatActivity)
        viewModel = ViewModelProvider(
            requireActivity(),
            factory
        )[TransactionViewModel::class.java]
    }
}