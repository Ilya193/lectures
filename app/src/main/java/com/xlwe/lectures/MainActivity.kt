package com.xlwe.lectures

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.xlwe.lectures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = (application as App).viewModel

        binding.progressBar.visibility = View.INVISIBLE

        binding.actionButton.setOnClickListener {
            binding.actionButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }

        viewModel.init(object : DataCallback {
            override fun provideText(text: String) = runOnUiThread {
                binding.actionButton.isEnabled = true
                binding.progressBar.visibility = View.INVISIBLE
                binding.textView.text = text
            }

            override fun provideIconRes(id: Int) = runOnUiThread {
                binding.iconView.setImageResource(id)
            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}
