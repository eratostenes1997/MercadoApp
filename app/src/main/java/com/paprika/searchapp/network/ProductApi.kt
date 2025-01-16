package com.paprika.searchapp.network

import com.paprika.searchapp.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("sites/MLC/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse
}