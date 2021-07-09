package com.rayhan.newnormalguide.ui.stats

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

class StatsViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "StatsViewModel"
    private val client = ApiConfig.getApiService(application)

    private var data: MutableList<ApiData> = mutableListOf()
    private val _statesData = MutableLiveData<List<ApiData>>()

    val statesData: LiveData<List<ApiData>> = _statesData

    fun getStatesData() {
        client.getAllStateData().enqueue(object : Callback<List<ApiData>> {
            @Suppress("SENSELESS_COMPARISON")
            override fun onResponse(call: Call<List<ApiData>>, response: Response<List<ApiData>>) {
                Log.i(TAG, "onResponse $response")
                if (response.isSuccessful) {

                    _statesData.value =
                        declareData(response.body()!!).filter {
                            it.dateChecked != null
                        }.map {
                            ApiData(
                                it.dateChecked,
                                it.death.coerceAtLeast(0),
                                it.negative.coerceAtLeast(0),
                                it.positive.coerceAtLeast(0),
                                it.deathIncrease.coerceAtLeast(0),
                                it.negativeIncrease.coerceAtLeast(0),
                                it.positiveIncrease.coerceAtLeast(0),
                                it.state
                            )
                        }

                    if (_statesData.value == null) {
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

    private fun declareData(body: List<ApiData>): List<ApiData> {
        for (i in 0..364) {
            data.add(body.get(i))
        }
        return data
    }
}