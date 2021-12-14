package com.boredom.cinema_food.ui.checkout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.data.entity.HistoryEntity
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.databinding.ActivityCheckoutBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.ui.cart.CartAdapter
import com.boredom.cinema_food.ui.checkout.promo.PromoActivity
import com.boredom.cinema_food.utils.DateUtils
import com.boredom.cinema_food.utils.NumberFormatterUtils.format
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var cartAdapter: CartAdapter

    private var currentTotalPrice by Delegates.notNull<Int>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPromoButton()


        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CheckoutViewModel::class.java]

        cartAdapter = CartAdapter(object : CartAdapter.OnItemClickListener {
            override fun onItemRemove(item: ItemOrderEntity, position: Int) {
                viewModel.deleteOrder(item)
            }

            override fun onItemPlus(itemId: Int, position: Int) {
                viewModel.plusQuantityById(itemId)
                viewModel.constraintItemQuantity()
            }

            override fun onItemMinus(itemId: Int, position: Int) {
                viewModel.minusQuantityById(itemId)
                viewModel.constraintItemQuantity()
            }
        })

        // Observe orders value
        viewModel.orders.observe(this, { orders ->

            cartAdapter.setCarts(orders)
            binding.tvSubtotalItem.text = "Sub Total Order (${orders.size} items)"

            // Total price variable will count price without discount
            val totalPrice = ArrayList<Int>()
            // Calculate total price without discount
            for (item in orders) {
                item.itemQuantity?.let { item.itemPrice?.times(it) }?.let { totalPrice.add(it) }
            }
            currentTotalPrice = totalPrice.sum()
            binding.tvSubtotalCost.text = "Rp.${format(currentTotalPrice)}"
            // This cost will change if theres a discount coupon extras
            binding.tvTotalPaymentCost.text = "Rp.${format(currentTotalPrice)}"
            binding.tvTotalPaymentCost2.text = "Rp.${format(currentTotalPrice)}"

            // This activity will get intent when user apply a coupon
            val extras = intent.extras
            if (extras != null) {
                val item = extras.getParcelable<CouponEntity>(EXTRA_COUPON)
                if (item != null) {
                    applyDiscount(item)
                }
            }
            setupOrderButton(orders)
        })
        with(binding.rvCheckout) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cartAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun applyDiscount(item: CouponEntity) {
        binding.tvDiscountTitle.text = item.title
        binding.tvDiscountDescription.text = item.description
        binding.tvDiscountCost.text = "Rp.${format(item.discount)}"

        // Show discount description in promo
        binding.tvDiscountDescription.visibility = View.VISIBLE

        // Show discount title and price in payment summary
        binding.tvTitleDiscountSummary.visibility = View.VISIBLE

        // Show discount cost in payment summary
        binding.tvDiscountCost.visibility = View.VISIBLE

        currentTotalPrice -= item.discount

        binding.tvTotalPaymentCost.text =
            "Rp.${format(currentTotalPrice)}"
        binding.tvTotalPaymentCost2.text =
            "Rp.${format(currentTotalPrice)}"
    }


    private fun setupOrderButton(orders: List<ItemOrderEntity>) {

        // Build items title with quantity
        val title = StringBuilder()
        orders.forEachIndexed { index, item ->
            viewModel.getItemQuantityWithId(item.itemId).observe(this, { quantity ->
                if (index == orders.size - 1) {
                    title.append("$quantity x ${item.itemName}")
                } else {
                    title.append("$quantity x ${item.itemName}, ")
                }
            })
        }

        binding.btnOrder.setOnClickListener {
            val isDeliveryChecked = binding.btnGroupDelivery.checkedButtonIds.isNotEmpty()
            val isPaymentChecked = binding.btnGroupPayment.checkedButtonIds.isNotEmpty()
            val isOrderedItemNotEmpty = cartAdapter.itemCount != 0

            if (isDeliveryChecked && isPaymentChecked && isOrderedItemNotEmpty) {
                val historyEntity = HistoryEntity(
                    0,
                    DateUtils.getCurrentLocalDate(),
                    title.toString(),
                    currentTotalPrice,
                    true
                )
                viewModel.insertHistory(historyEntity)
                viewModel.deleteOrders()
                finish()
            } else {
                Snackbar.make(
                    binding.toolbarLayout,
                    "Failed to Order Make Sure to Select All Order Methods and Make Sure Order Items is Not Empty",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupPromoButton() {
        binding.btnAddCoupon.setOnClickListener {
            Intent(this, PromoActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    companion object {
        const val EXTRA_COUPON = "extra_coupon"
    }
}