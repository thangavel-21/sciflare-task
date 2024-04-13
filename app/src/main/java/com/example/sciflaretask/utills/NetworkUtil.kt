package com.terareum.exchange.utills

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtil {

    fun networkConnectivityStatus(context: Context): Boolean {
        val networkManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapability = networkManager.getNetworkCapabilities(networkManager.activeNetwork)
        return if (networkCapability != null) {
            return when {
                networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else false
    }
}
