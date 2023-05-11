package com.example.a160420031_uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420031_uts_anmp.model.Kost
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoriteKostViewModel (application: Application)
    : AndroidViewModel(application){

    val kostLD = MutableLiveData<ArrayList<Kost>>()
    val kostLoadErrLD = MutableLiveData<Boolean>()
    val kostLoadingLD = MutableLiveData<Boolean>()

    val TAG = "volleytag"
    private var queue: RequestQueue? = null

    fun refresh() {
        kostLoadingLD.value = true
        kostLoadErrLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/zenocrife/ubaya-kost-json/kosts?favorite=true"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Kost>>() { }.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                kostLD.value = result
                kostLoadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                kostLoadErrLD.value = false
                kostLoadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}