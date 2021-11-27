package com.doughit.sinan.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doughit.sinan.R

class MainActivity : AppCompatActivity() {
    companion object{
        fun actionStart(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}