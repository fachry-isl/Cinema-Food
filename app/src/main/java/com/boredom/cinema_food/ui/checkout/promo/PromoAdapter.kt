package com.boredom.cinema_food.ui.checkout.promo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.databinding.ItemPromoCouponBinding

class PromoAdapter(private val listener: OnItemClick) :
    RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    private val listCoupons = ArrayList<CouponEntity>()

    // default checkedPosition for radio button
    // -1 means we don't select any item because the index start from zero
    private var checkedPosition: Int = -1

    fun setCoupons(list: List<CouponEntity>) {
        listCoupons.clear()
        listCoupons.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val itemPromoBinding =
            ItemPromoCouponBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PromoViewHolder(itemPromoBinding, listener)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val items = listCoupons[position]
        holder.bind(items, position)

    }

    override fun getItemCount(): Int = listCoupons.size

    inner class PromoViewHolder(
        private val binding: ItemPromoCouponBinding,
        private val listener: OnItemClick
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: CouponEntity, position: Int) {
            binding.tvDiscountTitle.text = items.title
            binding.tvDiscountDescription.text = items.description


            if (checkedPosition == position) {
                binding.itemPromo.strokeWidth = 5
                binding.itemPromo.strokeColor = itemView.context.getColor(R.color.green)
            } else {
                binding.itemPromo.strokeWidth = 0
            }

            binding.radioBtnItem.setOnClickListener {
                val lastCheckedPosition = checkedPosition
                checkedPosition = position
                notifyItemChanged(lastCheckedPosition)
                notifyItemChanged(checkedPosition)
                listener.itemClickListener(items)
            }
        }
    }
}

interface OnItemClick {
    fun itemClickListener(
        item: CouponEntity
    )
}