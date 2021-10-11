package com.firdavs.bnettesttask.utils

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkAvailable(): Boolean {
    val ConnectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = ConnectionManager.activeNetworkInfo
    if (networkInfo != null && networkInfo.isConnected)
        return true
    return false
}