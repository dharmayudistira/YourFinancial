package com.pandecode.yourfinancial.ui.add_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pandecode.yourfinancial.databinding.BottomSheetAddTransactionBinding
import com.pandecode.yourfinancial.utils.ViewModelFactory

class AddTransactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding : BottomSheetAddTransactionBinding? = null
    private val binding get() = _binding as BottomSheetAddTransactionBinding

    private lateinit var viewModel: AddTransactionViewModel

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
            dismiss()
        }

    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(activity as AppCompatActivity)
        viewModel = ViewModelProvider(
            this,
            factory
        )[AddTransactionViewModel::class.java]
    }
}