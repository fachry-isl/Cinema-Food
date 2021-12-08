package com.boredom.cinema_food.ui.cart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.boredom.cinema_food.data.entity.ItemOrderEntity
import com.boredom.cinema_food.databinding.FragmentCartBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.ui.cart.checkout.CheckoutActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init view model
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]

        // Observe orders value inside showRecyclerView function
        viewModel.orders.observe(viewLifecycleOwner, Observer(this::showRecyclerView))

        setupOrderButton()
    }

    private fun setupOrderButton() {
        binding.btnGoCheckout.setOnClickListener {
            Intent(context, CheckoutActivity::class.java).apply {
                startActivity(this)
            }
        }
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
        with(binding.rvCart) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cartAdapter
        }

        // If the data is not empty we hide the illustrator image
        if (cartAdapter.itemCount != 0) {
            showIllustrator(false)
        } else {
            showIllustrator(true)
        }

        //Prepare number formatter
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols

        symbols.groupingSeparator = '.'
        formatter.decimalFormatSymbols = symbols

        binding.tvTotalCartItems.text = "Total (${formatter.format(orders.size)} items)"

        val totalPrice = ArrayList<Int>()
        for (item in orders) {
            item.itemQuantity?.let { item.itemPrice?.times(it) }?.let { totalPrice.add(it) }
        }
        binding.tvTotalPrice.text = "Rp.${formatter.format(totalPrice.sum())}"
    }

    private fun showIllustrator(isIllustrator: Boolean) {
        if (isIllustrator) {
            binding.ivIllustrator.visibility = View.VISIBLE
            binding.tvIllustrator1.visibility = View.VISIBLE
            binding.tvIllustrator2.visibility = View.VISIBLE
        } else {
            binding.ivIllustrator.visibility = View.GONE
            binding.tvIllustrator1.visibility = View.GONE
            binding.tvIllustrator2.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}