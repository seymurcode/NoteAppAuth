package com.sirketismi.noteapp.util

import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker

fun Fragment.showDatePicker(onDateSelected : (Long)->Unit ) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select note date")
        .build()

    datePicker.addOnPositiveButtonClickListener {
        onDateSelected(it)
    }

    datePicker.addOnCancelListener {

    }

    datePicker.addOnDismissListener {

    }

    datePicker.show(childFragmentManager, "date_picker")
}