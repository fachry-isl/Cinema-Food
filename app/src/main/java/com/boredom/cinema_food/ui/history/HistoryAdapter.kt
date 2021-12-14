package com.boredom.cinema_food.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boredom.cinema_food.data.entity.HistoryEntity
import com.boredom.cinema_food.databinding.ItemHistoryBinding
import com.boredom.cinema_food.utils.NumberFormatterUtils.format

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var historyList = ArrayList<HistoryEntity>()

    fun setHistories(list: List<HistoryEntity>) {
        historyList.clear()
        historyList.addAll(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val itemHistoryBinding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(itemHistoryBinding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = historyList.size

    class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: HistoryEntity) {
            binding.tvDateHistory.text = item.date
            binding.tvTitleHistory.text = item.title
            binding.tvPriceHistory.text = "Rp.${format(item.price)}"

            if (item.isProcessed) showStatusText(true) else showStatusText(false)
        }

        private fun showStatusText(isProcessed: Boolean) {
            if (isProcessed) {
                binding.tvStatusHistoryProcessed.visibility = View.VISIBLE
                binding.tvStatusHistorySuccess.visibility = View.GONE
            } else {
                binding.tvStatusHistoryProcessed.visibility = View.GONE
                binding.tvStatusHistorySuccess.visibility = View.VISIBLE
            }
        }
    }
}