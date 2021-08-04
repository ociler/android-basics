package com.xing.android.androidbasics4

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("users?_quantity=10")
    suspend fun getUsers(): Response<UserResponse>


    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://fakerapi.it/api/v1/")
                .build()
                .create(MyApi::class.java)
        }
    }
}
