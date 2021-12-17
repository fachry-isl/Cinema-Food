package com.boredom.cinema_food.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.boredom.cinema_food.databinding.DialogSuccessCheckoutBinding
import com.boredom.cinema_food.ui.home.HomeActivity
import com.boredom.cinema_food.ui.home.HomeActivity.Companion.EXTRA_CHECKOUT_HISTORY
import com.boredom.cinema_food.ui.home.HomeActivity.Companion.EXTRA_CHECKOUT_SNACKBAR


class OrderProcessedDialog(
    private val activity: Activity
) : DialogFragment() {
    private var _binding: DialogSuccessCheckoutBinding? = null
    private val binding get() = _binding!!

    /** The system calls this to get the DialogFragment's layout, regardless
    of whether it's being displayed as a dialog or an embedded fragment. */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        _binding = DialogSuccessCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoHome.setOnClickListener {
            activity.finish()
            Intent(activity, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra(EXTRA_CHECKOUT_SNACKBAR, true)
                startActivity(this)
            }
        }

        binding.btnGoHistory.setOnClickListener {
            activity.finish()
            Intent(activity, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra(EXTRA_CHECKOUT_SNACKBAR, true)
                putExtra(EXTRA_CHECKOUT_HISTORY, true)
                startActivity(this)
            }
        }
    }

    /** The system calls this only when creating the layout in a dialog. */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}