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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        coroutineScope.launch {
            testData().collect {
                Log.d("attadag", it.toString())
            }
        }
    }

    private suspend fun testData(): Flow<Person> = flow {
        val users = mutableListOf<Person>()

        for (i in 0..9) {
            users.add(
                Person(
                    id = i,
                    name = "name $i",
                    surname = "surname $i",
                    age = i + 10
                )
            )
        }

        users.forEach {
            delay(1000)
            emit(it)
        }
    }
}

data class Person(
    val id: Int,
    val name: String,
    val surname: String,
    val age: Int
)