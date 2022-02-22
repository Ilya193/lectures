package com.xlwe.lectures

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xlwe.lectures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
