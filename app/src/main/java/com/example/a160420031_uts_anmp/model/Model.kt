package com.example.a160420031_uts_anmp.model

import com.google.gson.annotations.SerializedName

data class User(
    val user_id : Int,
    val username:String,
    val password:String,
    val firstName:String,
    val lastName:String,
    val createdAt:String,
    val alamat: String,
    val email:String,
    val profilePic:String
)

data class Kost(
    val id:String?,
    val name:String?,
    val gender:String?,
    val region:String?,
    val price:String?,
    @SerializedName("photoUrl")
    val photoURL:String,
    val alamat:String?,
    val luas:String?,
    val fasilitas:String?,
    val peraturan:String?,
    val x:String,
    val y:String,
    val rating:String?,
    val favorite:Boolean

)

