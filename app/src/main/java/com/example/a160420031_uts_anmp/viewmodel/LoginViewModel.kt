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

class LoginViewModel (Application: Application): AndroidViewModel(Application) {
    val userLD = MutableLiveData<ArrayList<User>>()
    val userLoginStat = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(username: String, password: String) { // function Rcek login
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/zenocrife/ubaya-kost-json/users"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<User>>(it, object :
                    TypeToken<ArrayList<User>>(){}.type)
                userLD.value = result
                Log.d("Error 2:",result.toString())
                for (user in result) {
                    if (user.username==username && user.password==password) { //check username and passwor
                        userLoginStat.value = true
                    }
                }
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}