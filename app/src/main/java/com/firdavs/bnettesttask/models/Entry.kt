package com.firdavs.bnettesttask.models

import java.text.SimpleDateFormat
import java.util.*

data class Entry(
    val id: String,
    val body: String,
    val da: String,
    val dm: String
) {
    fun getFormattedDa(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy\n hh:mm:ss")
        return formatter.format(Date(da.toLong()))
    }

    fun getFormattedDm(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy\n hh:mm:ss")
        return formatter.format(Date(dm.toLong()))
    }
}