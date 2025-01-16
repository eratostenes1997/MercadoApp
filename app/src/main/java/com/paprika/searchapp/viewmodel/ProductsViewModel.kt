package com.paprika.searchapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paprika.searchapp.models.Product
import com.paprika.searchapp.network.ProductRepository
import kotlinx.coroutines.launch


class ProductsViewModel:ViewModel() {
     val products = MutableLiveData<List<Product>>()
     val repository = ProductRepository()
     val isLoading = MutableLiveData<Boolean>()

    fun searchProducts(query: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val listProducts = repository.fetchAllProducts(query)
                products.postValue(listProducts)
                listProducts.forEach { product ->
                    Log.d("Product", "Title: ${product.title}, Thumbnail: ${product.thumbnail}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("CharacterViewModel", "Error loading characters: ${e.message}")
            }finally {
                isLoading.value = false
            }
        }
    }
}