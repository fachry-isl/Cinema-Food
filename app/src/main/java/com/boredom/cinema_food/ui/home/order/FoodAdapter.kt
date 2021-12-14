package com.boredom.cinema_food.ui.home.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.FoodEntity
import com.boredom.cinema_food.databinding.ItemFoodOrderBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FoodAdapter(
    private var listener: OnItemClickListener
) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private var foodList = ArrayList<FoodEntity>()

    private var clickedPosition = -1
    fun setFoods(list: List<FoodEntity>) {
        foodList.clear()
        foodList.addAll(list)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemFoodBinding =
            ItemFoodOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemFoodBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val items = foodList[position]

        holder.binding.tvFoodName.text = items.foodName
        Glide.with(holder.itemView.context)
            .load(items.image)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(holder.binding.ivFood)


        holder.binding.radioBtnItem.isChecked = position == clickedPosition
        if (position == clickedPosition) {
            holder.binding.itemFood.strokeColor =
                holder.itemView.context.resources.getColor(R.color.green)
            holder.binding.itemFood.strokeWidth = 5
        } else {
            holder.binding.itemFood.strokeWidth = 0
        }

        holder.binding.radioBtnItem.setOnClickListener {
            val copyOfLastCheckedPosition = clickedPosition
            clickedPosition = holder.adapterPosition
            listener.onItemClick(items)
            notifyItemChanged(copyOfLastCheckedPosition)
            notifyItemChanged(clickedPosition)

        }

    }

    override fun getItemCount(): Int = foodList.size


    class FoodViewHolder(val binding: ItemFoodOrderBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(
            item: FoodEntity?
        )
    }
}