package com.xlwe.lectures

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.xlwe.lectures.databinding.ActivityMainBinding
import com.xlwe.lectures.databinding.DialogBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val textWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            binding.textInputLayoutLogin.isErrorEnabled = false
        }
    }

    override fun onResume() {
        super.onResume()
        binding.textInputEditTextLogin.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.textInputEditTextLogin.removeTextChangedListener(textWatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null)
            state = SUCCESS

        savedInstanceState?.let {
            state = it.getInt("screenState")
        }

        when (state) {
            FAILED -> showDialog()
            SUCCESS -> {
                Snackbar.make(
                    binding.loginButton, "Success",
                    Snackbar.LENGTH_SHORT
                ).show()
                state = INITIAL
            }
        }

        /*binding.textInputEditTextLogin.listenChanges {
            binding.textInputLayoutLogin.isErrorEnabled = false
        }*/

        binding.textInputEditTextPassword.filters = arrayOf(InputFilter.LengthFilter(MAX_LENGTH))

        binding.loginButton.isEnabled = false
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            binding.loginButton.isEnabled = isChecked
        }

        binding.loginButton.setOnClickListener {
            val valid =
                android.util.Patterns.EMAIL_ADDRESS.matcher(binding.textInputEditTextLogin.text.toString()).matches()

            if (valid) {
                hideKeyboard(binding.textInputEditTextLogin)
                binding.contentLayout.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                state = PROGRESS

                Snackbar.make(
                    binding.loginButton, getString(R.string.valid_email_message),
                    Snackbar.LENGTH_SHORT
                ).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    state = FAILED

                    with(binding) {
                        contentLayout.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }

                    showDialog()

                }, 3000)
            }
            else {
                binding.textInputLayoutLogin.isErrorEnabled = true
                binding.textInputLayoutLogin.error = getString(R.string.invalid_email_message)
            }
        }
    }

    private fun showDialog() {
        val dialog = BottomSheetDialog(this)
        dialog.setCancelable(false)
        val view = DialogBinding.inflate(layoutInflater)
        view.closeButton.setOnClickListener {
            state = INITIAL
            dialog.dismiss()
        }

        dialog.setContentView(view.root)
        dialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("screenState", state)
    }

    private var state = INITIAL

    private companion object {
        const val MAX_LENGTH = 10

        const val INITIAL = 0
        const val PROGRESS = 1
        const val SUCCESS = 2
        const val FAILED = 3
    }

    private fun log(message: String) {
        Log.d("MainActivity", message)
    }
}

fun AppCompatActivity.hideKeyboard(view: View) {
    val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun TextInputEditText.listenChanges(block: (text: String) -> Unit) {
    addTextChangedListener(object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            block.invoke(s.toString())
        }
    })
}

fun TextInputEditText.setTextCorrectly(text: String) {
    setText(text)
    setSelection(text.length)
}

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {}
}