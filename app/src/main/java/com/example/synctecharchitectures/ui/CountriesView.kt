package com.example.synctecharchitectures.ui

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.synctecharchitectures.R
import com.example.synctecharchitectures.R.id
import com.example.synctecharchitectures.R.layout
import com.example.synctecharchitectures.databinding.ArchitectureActivityBinding
import com.example.synctecharchitectures.model.dto.Country
import com.example.synctecharchitectures.mvi.ui.CountryScreenState

class CountriesView(
    private val binding: ArchitectureActivityBinding,
    private val onRetry: () -> Unit
) {
    private var countriesAdapter: ArrayAdapter<String?>? = null
    private val countryNames: MutableList<String?> = ArrayList()
    private val context = binding.root.context

    init {
        prepareUI()
    }

    private fun prepareUI() {
        countriesAdapter = ArrayAdapter(context, layout.row_layout, id.listText, countryNames)
        binding.list.apply {
            adapter = countriesAdapter
            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(
                    context,
                    "You clicked " + countryNames[position],
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.retryButton.setOnClickListener{
            onRetry()
        }
    }

    fun updateMviUI(screenState: CountryScreenState) {
        countryNames.clear()
        screenState.countries?.forEach { country ->
            countryNames.add(country.countryName)
        }
        countriesAdapter?.notifyDataSetChanged()

        binding.apply {
            progress.isVisible = screenState.isLoading
            retryButton.isVisible = screenState.isFailed
            list.isVisible = !screenState.isLoading && !screenState.isFailed
        }
    }

    fun showMvpData(countryList: List<Country>) = binding.run {
        countryList.forEach { country ->
            countryNames.add(country.countryName)
        }
        progress.visibility = GONE
        retryButton.visibility = GONE
        list.visibility = VISIBLE
    }

    fun showMvpError() = binding.run {
        Toast.makeText(context, context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        progress.visibility = GONE
        list.visibility = GONE
        retryButton.visibility = VISIBLE
    }

    fun showMvpLoading() = binding.run {
        list.visibility = GONE
        retryButton.visibility = GONE
        progress.visibility = VISIBLE
    }

}
