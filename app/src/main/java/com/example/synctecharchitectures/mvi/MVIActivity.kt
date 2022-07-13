package com.example.synctecharchitectures.mvi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.synctecharchitectures.R
import com.example.synctecharchitectures.databinding.ActivityMviactivityBinding
import com.example.synctecharchitectures.databinding.ActivitySynctecharchBinding

class MVIActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMviactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVIActivity::class.java)
    }
}