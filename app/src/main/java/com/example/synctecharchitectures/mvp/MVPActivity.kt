package com.example.synctecharchitectures.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.synctecharchitectures.R
import com.example.synctecharchitectures.databinding.ActivityMvpactivityBinding
import com.example.synctecharchitectures.model.Country
import com.example.synctecharchitectures.mvp.presenter.MVPPresenter
import com.example.synctecharchitectures.mvp.view.MVPView

class MVPActivity : AppCompatActivity(), MVPView {
    private val listValues: MutableList<String?> = ArrayList()
    private var adapter: ArrayAdapter<String?>? = null
    private lateinit var list: ListView
    private lateinit var presenter: MVPPresenter
    private var retryButton: Button? = null
    private var progress: ProgressBar? = null
    private lateinit var binding: ActivityMvpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        drawScreen()
        presenter = MVPPresenter(this)
        presenter.initPresenter()
    }

    private fun drawScreen() {
        title = "MVP Activity"
        list = binding.list
        retryButton = binding.retryButton
        progress = binding.progress
        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.listText, listValues)
        list.adapter = adapter
        list.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this@MVPActivity,
                "You clicked " + listValues[position],
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun setValues(countries: List<Country>) {
        runOnUiThread {
            listValues.clear()
            countries.forEach {
                listValues.add(it.countryName)
            }
            retryButton!!.visibility = View.GONE
            progress!!.visibility = View.GONE
            list.visibility = View.VISIBLE
            adapter!!.notifyDataSetChanged()
        }
    }

    override fun onError() {
        runOnUiThread {
            Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            progress!!.visibility = View.GONE
            list.visibility = View.GONE
            retryButton!!.visibility = View.VISIBLE
        }
    }

    fun onRetry() {
        presenter.onRefresh()
        list.visibility = View.GONE
        retryButton!!.visibility = View.GONE
        progress!!.visibility = View.VISIBLE
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVPActivity::class.java)
    }
}