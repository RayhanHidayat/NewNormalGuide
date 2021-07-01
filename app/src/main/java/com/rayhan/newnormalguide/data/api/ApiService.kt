package com.rayhan.newnormalguide.data.api

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("us/daily.json")
    fun getAllData(): Call<List<ApiData>>

    @GET("states/daily.json")
    fun getAllStateData(): Call<List<ApiData>>
}