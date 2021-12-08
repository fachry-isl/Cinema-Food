package com.boredom.cinema_food.ui.cart.checkout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.databinding.ActivityCheckoutBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.ui.cart.CartAdapter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CheckoutViewModel::class.java]

        // Observe orders value inside showRecyclerView function
        viewModel.orders.observe(this, Observer(this::showRecyclerView))

        setupOrderButton()
    }

    @SuppressLint("SetTextI18n")
    private fun showRecyclerView(orders: List<ItemOrderEntity>) {
        cartAdapter = CartAdapter(object : CartAdapter.OnItemClickListener {
            override fun onItemRemove(item: ItemOrderEntity) {
                viewModel.deleteOrder(item)
            }

            override fun onItemPlus(itemId: Int) {
                viewModel.plusQuantityById(itemId)
                viewModel.constraintItemQuantity()
            }

            override fun onItemMinus(itemId: Int) {
                viewModel.minusQuantityById(itemId)
                viewModel.constraintItemQuantity()
            }
        })
        cartAdapter.setCarts(orders)
        with(binding.rvCheckout) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cartAdapter
        }

        // Prepare number formatter
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols
        symbols.groupingSeparator = '.'
        formatter.decimalFormatSymbols = symbols


        binding.tvSubtotalItem.text = "Sub Total Order (${orders.size} items)"

        val totalPrice = ArrayList<Int>()
        for (item in orders) {
            item.itemQuantity?.let { item.itemPrice?.times(it) }?.let { totalPrice.add(it) }
        }
        binding.tvSubtotalCost.text = "Rp.${formatter.format(totalPrice.sum())}"
    }


    private fun setupOrderButton() {
        // Nothing
    }
}