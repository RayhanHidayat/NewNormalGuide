package com.rayhan.newnormalguide.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rayhan.newnormalguide.data.api.ApiConfig
import com.rayhan.newnormalguide.data.api.ApiData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MainViewModel"
    private val client = ApiConfig.getApiService(application)

    private var data: MutableList<ApiData> = mutableListOf()
    private val _nationData = MutableLiveData<List<ApiData>>()
    val nationData: LiveData<List<ApiData>> = _nationData

    fun getNationalData() {
        client.getAllData().enqueue(object : Callback<List<ApiData>> {
            override fun onResponse(call: Call<List<ApiData>>, response: Response<List<ApiData>>) {
                Log.i(TAG, "onResponse $response")
                if (response.isSuccessful) {

                    _nationData.value = declareData(response.body())

                    if (_nationData.value == null) {
                        Log.w(TAG, "Did not receive valid response")
                        return
                    }

                } else {
                    Log.w(TAG, "invalid response in onResponse")
                }
            }

            override fun onFailure(call: Call<List<ApiData>>, t: Throwable) {
                Log.e(TAG, "onFailure $t")
            }
        })
    }

    private fun declareData(body: List<ApiData>?): List<ApiData>? {
        for (i in 0..364) {
            data.add(body?.get(i)!!)
        }
        return data
    }
}