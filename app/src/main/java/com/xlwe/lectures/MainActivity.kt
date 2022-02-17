package com.xlwe.lectures

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.xlwe.lectures.databinding.ActivityMainBinding
import java.net.URL


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //нет кэширования, проблемы с масштабированием,
        // проблемы с очень большим изображением
        val netImage = NetImage(URL, object : ImageCallback {
            override fun success(bitmap: Bitmap) = runOnUiThread {
                binding.imageView.setImageBitmap(bitmap)
            }

            override fun failed() = runOnUiThread {
                Snackbar.make(binding.imageView, "failed", Snackbar.LENGTH_SHORT).show()
            }
        })
        //netImage.start()

        /*Picasso.get().load(URL)
            .centerCrop()
            .resize(720, 1280)
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(binding.imageView)*/

        Glide.with(this)
            .load(URL)
            .override(720, 1280)
            .circleCrop()
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(binding.imageView)
    }

    companion object {
        const val URL =
            "https://i1.wallbox.ru/wallpapers/main2/201717/art-ogni-kosmos-zvezdy-uzory-tumannost-rossyp-mercanie.jpg"
    }
}

class NetImage(
    private val url: String,
    private val callback: ImageCallback
) : Thread() {
    override fun run() {
        super.run()
        try {
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            connection.getInputStream().use {
                callback.success(BitmapFactory.decodeStream(it))
            }
        } catch (e: Exception) {
            callback.failed()
        }
    }
}

interface ImageCallback {
    fun success(bitmap: Bitmap)
    fun failed()
}