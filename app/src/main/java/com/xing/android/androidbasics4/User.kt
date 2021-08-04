package com.xing.android.androidbasics4

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("data") val results: List<User>)

data class User(
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String,
    @SerializedName("ip") val ipAddress: String,
    @SerializedName("macAddress") val macAddress: String,
    @SerializedName("website") val website: String,
)
