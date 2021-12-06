package com.boredom.cinema_food.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.boredom.cinema_food.data.entity.MovieEntity
import com.boredom.cinema_food.databinding.FragmentHomeBinding
import com.boredom.cinema_food.utils.DataDummy


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
            val homeAdapter = HomeAdapter(object : HomeAdapter.OnItemClickListener {
                override fun onItemClick(items: MovieEntity) {
                    Toast.makeText(context, items.title, Toast.LENGTH_SHORT).show()
                }

            })
            homeAdapter.setMovies(DataDummy.generateDummyMovies())

            with(binding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = homeAdapter
                setHasFixedSize(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}