package com.boredom.cinema_food.ui.cart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init view model
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]


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

        // Observe orders value to keep ui data updated
        viewModel.orders.observe(viewLifecycleOwner, { orders ->
            cartAdapter.setCarts(orders)

            //Prepare number formatter
            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
            val symbols = formatter.decimalFormatSymbols

            symbols.groupingSeparator = '.'
            formatter.decimalFormatSymbols = symbols

            binding.tvTotalCartItems.text = "Total (${formatter.format(orders.size)} items)"

            val totalPrice = ArrayList<Int>()
            // Times all item with quantity and store it in List<Int>
            for (item in orders) {
                item.itemQuantity?.let { item.itemPrice?.times(it) }?.let { totalPrice.add(it) }
            }
            // Sum prices using function List.Sum()
            binding.tvTotalPrice.text = "Rp.${formatter.format(totalPrice.sum())}"

            // Show illustrator when data order empty
            if (cartAdapter.itemCount == 0) {
                showIllustrator(true)
            } else {
                showIllustrator(false)
            }

        })

        with(binding.rvCart) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cartAdapter
        }

        setupOrderButton()
    }

    private fun setupOrderButton() {
        binding.btnGoCheckout.setOnClickListener {
            Intent(context, CheckoutActivity::class.java).apply {
                startActivity(this)
            }
        }
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