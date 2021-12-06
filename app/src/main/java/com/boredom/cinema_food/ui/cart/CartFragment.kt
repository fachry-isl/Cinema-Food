package com.boredom.cinema_food.ui.cart

import android.annotation.SuppressLint
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
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]


        viewModel.orders.observe(viewLifecycleOwner, Observer(this::showRecyclerView))
    }

    @SuppressLint("SetTextI18n")
    private fun showRecyclerView(orders: List<ItemOrderEntity>) {
        cartAdapter = CartAdapter(object : CartAdapter.OnItemClickListener {
            override fun onItemRemove(item: ItemOrderEntity) {
                viewModel.deleteOrder(item)
            }

            override fun onItemPlus(itemId: Int) {
                viewModel.plusQuantityById(itemId)
                viewModel.constrainQuantity()
            }

            override fun onItemMinus(itemId: Int) {
                viewModel.minusQuantityById(itemId)
                viewModel.constrainQuantity()
            }
        })
        cartAdapter.setCarts(orders)
        with(binding.rvCart) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cartAdapter
        }

        binding.tvTotalCartItems.text = "Total (${orders.size} items)"

        val totalPrice = ArrayList<Int>()
        for (item in orders) {
            item.itemQuantity?.let { item.itemPrice?.times(it) }?.let { totalPrice.add(it) }
        }
        binding.tvTotalPrice.text = "Rp.${totalPrice.sum()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}