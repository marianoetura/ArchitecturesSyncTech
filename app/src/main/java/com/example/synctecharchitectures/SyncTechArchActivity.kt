package com.example.synctecharchitectures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.synctecharchitectures.databinding.ActivitySynctecharchBinding
import com.example.synctecharchitectures.mvi.MVIActivity
import com.example.synctecharchitectures.mvp.MVPActivity
import com.example.synctecharchitectures.mvvm.MVVMActivity

class SyncTechArchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySynctecharchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySynctecharchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ButtonMVI.setOnClickListener { onMVI() }
        binding.ButtonMVP.setOnClickListener { onMVP() }
        binding.ButtonMVVM.setOnClickListener { onMVVM() }

    }

    private fun onMVP() {
        startActivity(MVPActivity.getIntent(this.applicationContext))
    }

    private fun onMVVM() {
        startActivity(MVVMActivity.getIntent(this.applicationContext))
    }

    private fun onMVI() {
        startActivity(MVIActivity.getIntent(this.applicationContext))
    }


}