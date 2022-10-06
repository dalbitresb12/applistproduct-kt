package com.dalbitresb.applistproduct.responses

import com.google.gson.annotations.SerializedName

class ProductResponse {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("image")
    lateinit var image: String

    @SerializedName("imageType")
    lateinit var imageType: String
}