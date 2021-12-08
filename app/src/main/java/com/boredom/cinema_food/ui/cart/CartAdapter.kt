package com.boredom.cinema_food.ui.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.databinding.ItemCartBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(private var listener: OnItemClickListener) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var orderList = ArrayList<ItemOrderEntity>()

    fun setCarts(list: List<ItemOrderEntity>) {
        orderList.clear()
        orderList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemCartBinding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(itemCartBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val items = orderList[position]

        with(holder) {
            binding.tvItemTitle.text = items.itemName
            binding.tvItemDesc.text = items.itemDesc
            Glide.with(itemView.context)
                .load(items.itemImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(binding.ivItemCart)

            //Prepare formatter for number
            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.groupingSeparator = '.'
            formatter.decimalFormatSymbols = symbols

            binding.tvItemPrice.text =
                "Rp.${formatter.format(items.itemPrice?.times(items.itemQuantity!!))}"

            binding.ivItemRemove.setOnClickListener {
                listener.onItemRemove(items)
            }

            binding.tvItemQuantity.text = items.itemQuantity.toString()

            binding.ivItemPlus.setOnClickListener {
                listener.onItemPlus(items.itemId)
            }
            binding.ivItemMinus.setOnClickListener {
                listener.onItemMinus(items.itemId)
            }

        }
    }

    override fun getItemCount(): Int = orderList.size

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemRemove(item: ItemOrderEntity)
        fun onItemPlus(itemId: Int)
        fun onItemMinus(itemId: Int)
    }
}