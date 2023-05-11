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

class RegionKostViewModel(application: Application)
    : AndroidViewModel(application) {

    val regionLD = MutableLiveData<ArrayList<Kost>>()
    val regionLoadErrLD = MutableLiveData<Boolean>()
    val regionLoadingLD = MutableLiveData<Boolean>()

    val TAG = "volleytag"
    private var queue: RequestQueue? = null

    fun refresh() {
        regionLoadingLD.value = true
        regionLoadErrLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url =
            "https://my-json-server.typicode.com/zenocrife/ubaya-kost-json/kosts?region=Rungkut"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Kost>>() {}.type
                val result = Gson().fromJson<ArrayList<Kost>>(it, sType)
                regionLD.value = result
                regionLoadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                regionLoadErrLD.value = false
                regionLoadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}