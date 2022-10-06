package com.dalbitresb.applistproduct.services

import com.dalbitresb.applistproduct.responses.SearchProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {
    @GET("/food/products/search")
    fun searchProducts(
        @Query("query") query: String,
        @Query("number") count: Int
    ): Call<SearchProductsResponse>
}