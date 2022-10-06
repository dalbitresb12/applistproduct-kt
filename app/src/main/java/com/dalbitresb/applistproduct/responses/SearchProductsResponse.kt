package com.dalbitresb.applistproduct.responses

import com.google.gson.annotations.SerializedName

class SearchProductsResponse {
    @SerializedName("products")
    lateinit var products: List<ProductResponse>

    @SerializedName("totalProducts")
    var totalProducts: Int = 0

    @SerializedName("type")
    lateinit var type: String

    @SerializedName("offset")
    var offset: Int = 0

    @SerializedName("number")
    var number: Int = 0
}