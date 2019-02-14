package com.merseyside.amazingtestapp.utils

import com.merseyside.admin.merseylibrary.system.NetworkManager
import com.merseyside.amazingtestapp.AmazingTestApplication

class Utils {

    companion object {

        private val TAG = "Utils"

        fun isNetworkConnected(): Boolean {
            return NetworkManager.isOnline(AmazingTestApplication.application)
        }
    }
}