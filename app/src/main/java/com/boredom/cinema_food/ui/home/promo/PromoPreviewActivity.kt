package com.boredom.cinema_food.ui.home.promo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.boredom.cinema_food.data.entity.CouponEntity
import com.boredom.cinema_food.databinding.ActivityPromoBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.ui.checkout.promo.OnItemClick
import com.boredom.cinema_food.ui.checkout.promo.PromoAdapter
import com.boredom.cinema_food.ui.checkout.promo.PromoViewModel

class PromoPreviewActivity : AppCompatActivity() {
    private lateinit var viewModel: PromoViewModel
    private lateinit var binding: ActivityPromoBinding

    private lateinit var promoAdapter: PromoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[PromoViewModel::class.java]

        promoAdapter = PromoAdapter(object : OnItemClick {
            override fun itemClickListener(item: CouponEntity) {
                // Nothing will happen preview only
            }

        })
        viewModel.coupons.observe(this, { coupons ->
            promoAdapter.setCoupons(coupons)

            with(binding.rvCoupon) {
                layoutManager = LinearLayoutManager(context)
                adapter = promoAdapter
                setHasFixedSize(true)
            }
        })

        binding.layoutPriceSaved.visibility = View.GONE
    }
}