package com.example.synctecharchitectures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.synctecharchitectures.databinding.SyncTechArchitecturesActivityBinding
import com.example.synctecharchitectures.mvi.ui.MVIActivity
import com.example.synctecharchitectures.mvp.MVPActivity
import com.example.synctecharchitectures.mvvm.MVVMActivity

class SyncTechArchActivity : AppCompatActivity() {

    private lateinit var binding: SyncTechArchitecturesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SyncTechArchitecturesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            ButtonMVI.setOnClickListener { onMVI() }
            ButtonMVP.setOnClickListener { onMVP() }
            ButtonMVVM.setOnClickListener { onMVVM() }
        }
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
