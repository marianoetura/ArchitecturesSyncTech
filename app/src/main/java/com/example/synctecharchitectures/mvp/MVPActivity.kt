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
        title = "MVP Activity"
        presenter = MVPPresenter(this)
        presenter.initPresenter()
        list = binding.list
        retryButton = findViewById(R.id.retryButton)
        progress = findViewById(R.id.progress)
        adapter = ArrayAdapter(this, R.layout.row_layout, listValues)
        list.setAdapter(adapter)
        list.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this@MVPActivity,
                "You clicked " + listValues[position],
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    override fun setValues(countries: List<String>) {
        listValues.clear()
        listValues.addAll(countries)
        retryButton!!.visibility = View.GONE
        progress!!.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter!!.notifyDataSetChanged()
    }

    override fun onError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        progress!!.visibility = View.GONE
        list.visibility = View.GONE
        retryButton!!.visibility = View.VISIBLE
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