package com.pandecode.yourfinancial.ui.transaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pandecode.yourfinancial.R
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import com.pandecode.yourfinancial.databinding.ItemTransactionBinding
import com.pandecode.yourfinancial.utils.TransactionType

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private val listTransaction = mutableListOf<TransactionEntity>()

    fun setListTransaction(transactions: MutableList<TransactionEntity>) {
        this.listTransaction.clear()
        listTransaction.addAll(transactions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.ViewHolder {
        return ViewHolder(
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        holder.bind(listTransaction[position], position)
    }

    override fun getItemCount() = listTransaction.size

    inner class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: TransactionEntity, position: Int) {

            binding.tvTitleItemTransaction.text = transaction.title
            binding.tvAmountItemTransaction.text =
                binding.root.context.getString(R.string.amount_placholder, transaction.amount)
            binding.tvStatusItemTransaction.text = transaction.status.text
            binding.tvNotesItemTransaction.text = transaction.notes

            if (transaction.isExpand) {
                binding.tvNotesItemTransaction.visibility = View.VISIBLE
                binding.btnExpandItemTransaction.setImageResource(R.drawable.ic_expand_up)
            } else {
                binding.tvNotesItemTransaction.visibility = View.GONE
                binding.btnExpandItemTransaction.setImageResource(R.drawable.ic_expandable_down)
            }

            when (transaction.type) {
                TransactionType.REVENUE -> {
                    binding.cardItemTransaction.strokeColor =
                        ContextCompat.getColor(binding.root.context, R.color.green_500)
                }
                TransactionType.EXPANSE -> {
                    binding.cardItemTransaction.strokeColor =
                        ContextCompat.getColor(binding.root.context, R.color.red_500)
                }
            }

            binding.btnExpandItemTransaction.setOnClickListener {
                transaction.isExpand = !transaction.isExpand
                notifyItemChanged(position)
            }

        }
    }
}