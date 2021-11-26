package com.doughit.sinan

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SiNanApplication : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}