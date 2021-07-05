package com.rayhan.newnormalguide.ui.detail_stats

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

class DetStatsViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "DetailStatsViewModel"
    private val client = ApiConfig.getApiService(application)

    private var data: MutableList<ApiData> = mutableListOf()
    private val _nationData = MutableLiveData<List<ApiData>>()
    private val _statesData = MutableLiveData<List<ApiData>>()

    val nationData: LiveData<List<ApiData>> = _nationData
    val statesData: LiveData<List<ApiData>> = _statesData

    fun getNationalData() {
        client.getAllData().enqueue(object : Callback<List<ApiData>> {
            override fun onResponse(call: Call<List<ApiData>>, response: Response<List<ApiData>>) {
                Log.i(TAG, "onResponse $response")
                if (response.isSuccessful) {

                    for (i in 0..364) {
                        data.add(response.body()?.get(i)!!)
                    }
                    _nationData.value = data

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

    fun getStatesData() {
        client.getAllStateData().enqueue(object : Callback<List<ApiData>> {
            override fun onResponse(call: Call<List<ApiData>>, response: Response<List<ApiData>>) {
                Log.i(TAG, "onResponse $response")
                if (response.isSuccessful) {
                    _statesData.value = response.body()

                    if (_statesData.value == null) {
                        Log.w(TAG, "Did not receive valid response")
                        return
                    }

                    _statesData.value!!.reversed().groupBy {
                        it.state
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
}