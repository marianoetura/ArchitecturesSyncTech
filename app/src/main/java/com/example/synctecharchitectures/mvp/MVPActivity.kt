package com.example.synctecharchitectures.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.synctecharchitectures.R
import com.example.synctecharchitectures.mvi.MVIActivity

class MVPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvpactivity)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVPActivity::class.java)
    }
}