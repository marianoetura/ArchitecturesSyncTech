package com.example.synctecharchitectures.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.synctecharchitectures.R
import com.example.synctecharchitectures.databinding.ActivityMvvmactivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MVVMActivity : AppCompatActivity() {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.Main) }
    private lateinit var binding: ActivityMvvmactivityBinding
    private val listValues: MutableList<String?> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String?>
    private lateinit var viewModel: MVVMViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "MVVM Activity"

        binding = ActivityMvvmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MVVMViewModel::class.java]

        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.listText, listValues)
        binding.list.adapter = adapter
        binding.list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this@MVVMActivity,
                "You clicked " + listValues[position],
                Toast.LENGTH_SHORT
            ).show()
        }

        scope.launch {
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this@MVVMActivity) { countries ->
            listValues.clear()
            countries.forEach {
                listValues.add(it.countryName)
            }
            binding.apply {
                retryButton.visibility = View.GONE
                progress.visibility = View.GONE
                list.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.error.observe(this@MVVMActivity) { isFailed ->
            if (isFailed) onError()
        }
    }

    fun onError() {
        runOnUiThread {
            Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
            binding.list.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener { onRetry() }
        }
    }

    fun onRetry() {
        viewModel.onRefresh()
        binding.apply {
            list.visibility = View.GONE
            retryButton.visibility = View.GONE
            progress.visibility = View.VISIBLE
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}