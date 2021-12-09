package com.boredom.cinema_food.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton


class CustomToggleableRadioButton : AppCompatRadioButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @Override
    override fun toggle() {
        if (isChecked) {
            if (parent is RadioGroup) {
                (parent as RadioGroup).clearCheck()
            }
        } else {
            isChecked = true
        }
    }

}