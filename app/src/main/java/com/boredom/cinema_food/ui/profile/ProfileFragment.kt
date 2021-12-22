package com.boredom.cinema_food.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.boredom.cinema_food.R
import com.boredom.cinema_food.databinding.FragmentProfileBinding
import com.boredom.cinema_food.ui.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel
    private lateinit var mFirebaseAuth: FirebaseAuth
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        viewModel.getItemHistoriesCount().observe(viewLifecycleOwner, { count ->
            binding.tvTotalOrder.text = count.toString()
        })

        viewModel.getItemCouponsCount().observe(viewLifecycleOwner, { count ->
            binding.tvTotalCoupon.text = count.toString()
        })

        // Initialize Firebase Component
        mFirebaseAuth = FirebaseAuth.getInstance()

        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                populateUserInformation(user)
            }
        }

        binding.btnLogout.setOnClickListener {
            // Log out
            AuthUI.getInstance().signOut(requireContext())
        }
    }

    private fun populateUserInformation(user: FirebaseUser) {
        Glide.with(requireContext())
            .load(user.photoUrl)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivUserProfile)
        binding.tvUsername.text = user.displayName
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(mAuthStateListener as FirebaseAuth.AuthStateListener)
    }

    override fun onPause() {
        super.onPause()
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener as FirebaseAuth.AuthStateListener)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}