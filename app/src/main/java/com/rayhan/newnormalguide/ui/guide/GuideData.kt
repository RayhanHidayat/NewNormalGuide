package com.rayhan.newnormalguide.ui.guide

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GuideData(
    val urlImg: String?,
    val title: String,
    val symbolImg: Int?,
    val type: String,
    val source: String,
    val writer: String,
    val header: String,
    val content: String
) : Parcelable
