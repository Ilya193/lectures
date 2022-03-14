package com.xlwe.lectures

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.xlwe.lectures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var numbersAdapter: NumbersRecyclerAdapter

    private lateinit var mainList: List<Note>
    private var filteredList = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        numbersAdapter = NumbersRecyclerAdapter(emptyList(), this)
        binding.recyclerView.adapter = numbersAdapter

        viewModel.listNumbers.observe(this) {
            mainList = it.toList()
            numbersAdapter.update(mainList)
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val filterText = s.toString()

                if (s!!.isEmpty()) {
                    viewModel.update()
                    filteredList.clear()
                }
                else {
                    mainList.forEach { note ->
                        if (note.text.contains(filterText))
                            filteredList.add(note)

                        mainList = filteredList.toList()
                        numbersAdapter.update(mainList)
                    }
                }

            }
        })
    }

    override fun onClick(position: Int) {
        viewModel.delete(position)
    }
}
