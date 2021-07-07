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
    private val _statesData = MutableLiveData<Map<String, List<ApiData>>>()

    val nationData: LiveData<List<ApiData>> = _nationData
    val statesData: LiveData<Map<String, List<ApiData>>> = _statesData

    fun getNationalData() {
        client.getAllData().enqueue(object : Callback<List<ApiData>> {
            override fun onResponse(call: Call<List<ApiData>>, response: Response<List<ApiData>>) {
                Log.i(TAG, "onResponse $response")
                if (response.isSuccessful) {

                    _nationData.value = declareData(response.body()!!).map {
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
                        }.reversed().groupBy {
                            it.state.toString()
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