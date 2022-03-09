package com.xlwe.lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.xlwe.lectures.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getCount()

        viewModel.countValue.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("attadag", it.data ?: "")
                }

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("attadag", "Loading")
                }
            }
        }
    }
}
