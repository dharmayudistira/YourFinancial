package com.pandecode.yourfinancial.ui.add_transaction

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pandecode.yourfinancial.R
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import com.pandecode.yourfinancial.databinding.BottomSheetAddTransactionBinding
import com.pandecode.yourfinancial.ui.transaction.TransactionViewModel
import com.pandecode.yourfinancial.utils.TransactionStatus
import com.pandecode.yourfinancial.utils.TransactionType
import org.koin.android.ext.android.inject

class AddTransactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetAddTransactionBinding? = null
    private val binding get() = _binding as BottomSheetAddTransactionBinding

    private val viewModel by inject<TransactionViewModel>()

    var selectedTransaction : TransactionEntity? = null
    var isUpdate = false

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

        if (selectedTransaction != null) {
            setupUI(selectedTransaction)
            binding.btnSaveAddTransaction.text = resources.getString(R.string.update)
        }

        binding.btnSaveAddTransaction.setOnClickListener {
            if (validateInput()) {
                if (isUpdate) {
                    selectedTransaction?.let {
                        updateTransaction(it)
                    }
                }else {
                    addTransaction()
                }
            }
        }

    }

    private fun updateTransaction(transaction: TransactionEntity) {
        val amount = binding.edtInputAmountAddTransaction.editText?.text.toString().toDouble()
        val title = binding.edtInputTitleAddTransaction.editText?.text.toString()
        val type = getTransactionType()
        val status = getTransactionStatus()
        val notes = binding.edtInputNotesAddTransaction.editText?.text?.toString()

        val transactionEntity = TransactionEntity(
            id = transaction.id,
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

    private fun setupUI(transaction: TransactionEntity?) {
        transaction?.let {
            binding.edtInputAmountAddTransaction.editText?.setText(transaction.amount.toString())
            binding.edtInputTitleAddTransaction.editText?.setText(transaction.title)

            when(transaction.status) {
                TransactionStatus.BELUM_LUNAS -> {
                    binding.rbBelumLunasAddTransaction.isChecked = true
                }
                TransactionStatus.LUNAS -> {
                    binding.rbLunasAddTransaction.isChecked = true
                }
            }

            when(transaction.type) {
                TransactionType.EXPANSE -> {
                    binding.rbExpanseAddTransaction.isChecked = true
                }
                TransactionType.REVENUE -> {
                    binding.rbRevenueAddTransaction.isChecked = true
                }
            }

            binding.edtInputNotesAddTransaction.editText?.setText(transaction.notes)
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

        dismiss() //dismiss the bottom sheet
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
}