package com.recon.dailyroutine.AdditionalClasses

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionManager  {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected == true
        }
    }
}
