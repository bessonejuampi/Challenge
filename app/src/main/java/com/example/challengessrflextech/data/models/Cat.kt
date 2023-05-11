package com.example.challengessrflextech.data.models

import com.google.gson.annotations.SerializedName

data class Cat (
    @SerializedName("breeds") val breeds : List<Breeds>?,
    @SerializedName("id") val id : String,
    @SerializedName("url") val url : String,
    @SerializedName("width") val width : Int,
    @SerializedName("height") val height : Int,
        ){
}