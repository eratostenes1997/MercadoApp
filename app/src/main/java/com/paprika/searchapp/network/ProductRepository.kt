package com.paprika.searchapp.network

import com.paprika.searchapp.models.Product

class ProductRepository {

    val api = RetrofitClient.api

    suspend fun fetchAllProducts(query: String): List<Product>{
        return api.searchProducts(query).results
    }
}