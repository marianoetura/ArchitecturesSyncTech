package com.example.synctecharchitectures.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.synctecharchitectures.R
import com.example.synctecharchitectures.mvi.MVIActivity

class MVVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}