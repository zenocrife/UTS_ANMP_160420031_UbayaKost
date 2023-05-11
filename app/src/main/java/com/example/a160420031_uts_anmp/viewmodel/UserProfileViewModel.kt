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
import com.example.a160420031_uts_anmp.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserProfileViewModel (application: Application):
AndroidViewModel(application){

    val userLD = MutableLiveData<ArrayList<User>>()
    val userLoadErrLD = MutableLiveData<Boolean>()
    val userLoadLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        userLoadLD.value = true
        userLoadErrLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/zenocrife/ubaya-kost-json/users?user_id=1"

        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<User>>() { }.type
                val result = Gson().fromJson<ArrayList<User>>(it, sType)
                userLD.value = result
                userLoadLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                userLoadErrLD.value = false
                userLoadLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}