package com.boredom.cinema_food.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.boredom.cinema_food.R
import com.boredom.cinema_food.data.entity.MovieEntity
import com.boredom.cinema_food.databinding.FragmentHomeBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.boredom.cinema_food.utils.DayUtils


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeAdapter = HomeAdapter()

            // Setup spinner and pass the selected spinner data to the adapter
            setupSpinner()

            with(binding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = homeAdapter
                setHasFixedSize(true)
            }
        }
    }


    private fun setupSpinner() {
        val spinner = binding.spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            context as Context,
            R.array.planets_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                var sort = ""

                when (position) {
                    0 -> sort = DayUtils.SUNDAY
                    1 -> sort = DayUtils.MONDAY
                    2 -> sort = DayUtils.TUESDAY
                    3 -> sort = DayUtils.WEDNESDAY
                    4 -> sort = DayUtils.THURSDAY
                    5 -> sort = DayUtils.FRIDAY
                    6 -> sort = DayUtils.SATURDAY
                }
                viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Default value will be pass
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val movieObserver = Observer<List<MovieEntity>> { movieList ->
        if (movieList != null) {
            homeAdapter.setMovies(movieList)
            homeAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}