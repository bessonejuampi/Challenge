package com.example.challengessrflextech.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class Cat (
    @SerializedName("breeds") val breeds : List<Breeds>?,
    @SerializedName("id") val id : String,
    @SerializedName("url") val url : String,
    @SerializedName("width") val width : Int,
    @SerializedName("height") val height : Int,
):Parcelable{
}