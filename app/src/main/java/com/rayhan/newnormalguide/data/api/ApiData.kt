package com.rayhan.newnormalguide.data.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApiData(
    @SerializedName("dateChecked")
    val dateChecked: Date,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("positive")
    val positive: Int,
    @SerializedName("death")
    val death: Int,
    @SerializedName("negativeIncrease")
    val negativeIncrease: Int,
    @SerializedName("positiveIncrease")
    val positiveIncrease: Int,
    @SerializedName("deathIncrease")
    val deathIncrease: Int,
    @SerializedName("state")
    val state: String
)
