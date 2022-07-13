package com.example.synctecharchitectures.mvi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.synctecharchitectures.R

class MVIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mviactivity)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVIActivity::class.java)
    }
}