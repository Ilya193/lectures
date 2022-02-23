package com.xlwe.lectures

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.xlwe.lectures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var numbersAdapter: NumbersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        numbersAdapter = NumbersRecyclerAdapter(emptyList(), this)
        binding.recyclerView.adapter = numbersAdapter

        viewModel.listNumbers.observe(this) {
            numbersAdapter.update(it.toList())
        }
    }

    override fun onClick(position: Int) {
        viewModel.delete(position)
    }
}
