package com.example.thuctap

import android.app.Application
import com.cloudinary.android.MediaManager
import com.example.thuctap.common.DataLocal
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationFirebaseAuth:Application() {
//    override fun onCreate() {
//        super.onCreate()
//        val config = HashMap<Any?, Any?>()
//        config["cloud_name"] = DataLocal.CLOUD_NAME
//        config["api_key"] = DataLocal.API_KEY
//        config["api_secret"] = DataLocal.API_SECRET
////        config.put("secure", true);
//        MediaManager.init(this, config)
//    }
}