package com.sirketismi.noteapp.util

import java.text.SimpleDateFormat
import java.util.Date


//yyyy MMM EEE hh:mm:ss
fun Date.format(format : String = "yyyy MMM EEE") : String {
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(this)
}