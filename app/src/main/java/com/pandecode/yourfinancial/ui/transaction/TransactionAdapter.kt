package com.pandecode.yourfinancial.ui.transaction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pandecode.yourfinancial.R
import com.pandecode.yourfinancial.data.local.room.entity.TransactionEntity
import com.pandecode.yourfinancial.databinding.ItemTransactionBinding
import com.pandecode.yourfinancial.utils.RupiahFormatter
import com.pandecode.yourfinancial.utils.TransactionType

class TransactionAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private val listTransaction = mutableListOf<TransactionEntity>()

    fun setListTransaction(transactions: MutableList<TransactionEntity>) {
        this.listTransaction.clear()
        listTransaction.addAll(transactions)
        listTransaction.reverse()
        notifyDataSetChanged()
    }

    fun getAllRevenues(): Double {
        var amounts = 0.0
        for (transaction in listTransaction) {
            if (transaction.type == TransactionType.REVENUE) {
                amounts += transaction.amount
            }
        }
        return amounts
    }

    fun getAllExpanse(): Double {
        var amounts = 0.0
        for (transaction in listTransaction) {
            if (transaction.type == TransactionType.EXPANSE) {
                amounts += transaction.amount
            }
        }
        return amounts
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
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(transaction: TransactionEntity, position: Int) {

            binding.tvTitleItemTransaction.text = transaction.title
            binding.tvAmountItemTransaction.text = RupiahFormatter.toRupiah(transaction.amount)
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

        override fun onClick(v: View?) {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(listTransaction[bindingAdapterPosition])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(transaction: TransactionEntity)
    }
}