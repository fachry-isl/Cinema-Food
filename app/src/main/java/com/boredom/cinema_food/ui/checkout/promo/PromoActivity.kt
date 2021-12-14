package com.boredom.cinema_food.ui.checkout.promo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.databinding.ActivityPromoBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.ui.checkout.CheckoutActivity
import com.boredom.cinema_food.ui.checkout.CheckoutActivity.Companion.EXTRA_COUPON
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class PromoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPromoBinding
    private lateinit var viewModel: PromoViewModel
    private lateinit var promoAdapter: PromoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[PromoViewModel::class.java]

        viewModel.coupons.observe(this, Observer(this::showRecyclerView))
    }

    private fun setupApplyCouponButton(item: CouponEntity) {
        binding.btnApplyCoupon.setOnClickListener {
            // Making Intent and Extra for applied discount
            Intent(this, CheckoutActivity::class.java).apply {
                putExtra(EXTRA_COUPON, item)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
            // Delete applied discount under construction
            // viewModel.deleteCoupon(item)
            finish()
        }
    }

    private fun showRecyclerView(coupons: List<CouponEntity>) {
        //Prepare number formatter
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols

        symbols.groupingSeparator = '.'
        formatter.decimalFormatSymbols = symbols

        promoAdapter = PromoAdapter(object : OnItemClick {
            @SuppressLint("SetTextI18n")
            override fun itemClickListener(item: CouponEntity) {
                // Set discount price text for selected coupon
                binding.tvTotalPaymentCost.text = "Rp.${formatter.format(item.discount)}"
                setupApplyCouponButton(item)
            }

        })
        promoAdapter.setCoupons(coupons)
        with(binding.rvCoupon) {
            layoutManager = LinearLayoutManager(context)
            adapter = promoAdapter
            setHasFixedSize(true)
        }
    }
}