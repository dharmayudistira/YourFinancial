package com.pandecode.yourfinancial.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import com.pandecode.yourfinancial.databinding.FragmentTransactionBinding
import com.pandecode.yourfinancial.ui.add_transaction.AddTransactionBottomSheet
import com.pandecode.yourfinancial.utils.RupiahFormatter
import org.koin.android.ext.android.inject

class TransactionFragment : Fragment(), TransactionAdapter.OnItemClickListener {
    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding as FragmentTransactionBinding

    private val viewModel by inject<TransactionViewModel>()
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()

        observeTransaction()

        binding.btnAddTransactionTransaction.setOnClickListener {
            val bottomSheet = AddTransactionBottomSheet()

            activity?.supportFragmentManager?.let {
                bottomSheet.show(it, AddTransactionBottomSheet::class.java.simpleName)
            }
        }
    }

    private fun observeTransaction() {
        viewModel.listTransaction.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                showEmpty(false)

                transactionAdapter.setListTransaction(it)

                val totalRevenues = transactionAdapter.getAllRevenues()
                val totalExpanses = transactionAdapter.getAllExpanse()
                setOverview(totalRevenues, totalExpanses)
                setCurrentBalance(totalRevenues - totalExpanses)
            } else {
                showEmpty(true)
                setOverview(0.0, 0.0)
                setCurrentBalance(0.0 - 0.0)
            }
        })
    }

    private fun setCurrentBalance(currentBalance: Double) {
        binding.layoutTransactionOverview.tvYourBalanceTransaction.text =
            RupiahFormatter.toRupiah(currentBalance)
    }

    private fun setOverview(revenues: Double, expanses: Double) {
        binding.layoutTransactionOverview.tvTotalRevenueTransaction.text =
            RupiahFormatter.toRupiah(revenues)
        binding.layoutTransactionOverview.tvTotalExpanseTransaction.text =
            RupiahFormatter.toRupiah(expanses)
    }

    private fun showEmpty(state: Boolean) {
        if (state) {
            binding.layoutTransactionHistory.lottieEmptyTransaction.visibility = View.VISIBLE
            binding.layoutTransactionHistory.rvHistoryTransaction.visibility = View.GONE
        } else {
            binding.layoutTransactionHistory.lottieEmptyTransaction.visibility = View.GONE
            binding.layoutTransactionHistory.rvHistoryTransaction.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerview() {
        transactionAdapter = TransactionAdapter(this)

        with(binding.layoutTransactionHistory.rvHistoryTransaction) {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

    }

    override fun onItemClick(transaction: TransactionEntity) {
        val bottomSheet = AddTransactionBottomSheet()
        bottomSheet.selectedTransaction = transaction
        bottomSheet.isUpdate = true

        activity?.supportFragmentManager?.let {
            bottomSheet.show(it, AddTransactionBottomSheet::class.java.simpleName)
        }
    }


}