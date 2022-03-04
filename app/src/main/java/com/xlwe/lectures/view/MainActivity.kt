package com.xlwe.lectures.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.xlwe.lectures.app.App
import com.xlwe.lectures.databinding.ActivityMainBinding
import com.xlwe.lectures.model.BaseModel
import com.xlwe.lectures.model.Model
import com.xlwe.lectures.presenter.BasePresenter

class MainActivity : AppCompatActivity(), Model.ResultCallback {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = (application as App).presenter
        presenter.attachView(this)

        binding.changeName.setOnClickListener {
            presenter.setFullName(binding.fullName.text.toString())
        }

        binding.changeEmail.setOnClickListener {
            presenter.setEmail(binding.textEmail.text.toString())
        }
    }

    override fun provideInfo(str: String) {
        Snackbar.make(binding.root, str, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
