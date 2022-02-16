package com.xlwe.lectures

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.xlwe.lectures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fullText = getString(R.string.full_text)
        val button = getString(R.string.button)

        val spannableString = SpannableString(fullText)

        val buttonClickable = MyClickableSpan {
            Snackbar.make(it, "Click", Snackbar.LENGTH_SHORT).show()
        }

        spannableString.setSpan(
            buttonClickable,
            fullText.indexOf(button),
            fullText.indexOf(button) + button.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textView.run {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }
}

class MyClickableSpan(private val callback: (view: View) -> Unit) : ClickableSpan() {
    override fun onClick(widget: View) {
        callback(widget)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = true
        ds.color = Color.parseColor("#FF0000")
    }
}