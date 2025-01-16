package com.paprika.searchapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("price") val price: Int,

): Serializable
