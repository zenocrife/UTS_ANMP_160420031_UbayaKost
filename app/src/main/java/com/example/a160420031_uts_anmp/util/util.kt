package com.example.a160420031_uts_anmp.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.a160420031_uts_anmp.R
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.kost_list_item.view.*
import java.lang.Exception

fun ImageView.loadImage(url: String?,progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
                Log.d("Success", "Image successfully loaded")
            }
            override fun onError(e: Exception?) {
                Log.d("Failed", "Could not load image")
            }

        })
}
fun ImageView.loadImage2(url: String?) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                Log.d("Success", "Image successfully loaded")
            }
            override fun onError(e: Exception?) {
                Log.d("Failed", "Could not load image")
            }

        })
}

