package com.dalbitresb.applistproduct.fetchers

import com.dalbitresb.applistproduct.models.Product
import com.dalbitresb.applistproduct.responses.SearchProductsResponse
import com.dalbitresb.applistproduct.services.ProductsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductFetcher(endpoint: String, apiKey: String) {
    private class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url()
                .newBuilder()
                .addQueryParameter("apiKey", apiKey)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return chain.proceed(request)
        }
    }

    private class UserAgentInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .addHeader(
                    "User-Agent",
                    "AppListProduct (https://github.com/dalbitresb12/applistproduct-kt)"
                )
                .build()

            return chain.proceed(request)
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(apiKey))
        .addInterceptor(UserAgentInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(endpoint)
        .client(client)
        .build()

    private val service = retrofit.create(ProductsService::class.java)

    fun searchProducts(query: String, count: Int = 10, callback: (List<Product>) -> Unit) {
        service.searchProducts(query, count).enqueue(object : Callback<SearchProductsResponse> {
            override fun onResponse(
                call: Call<SearchProductsResponse>,
                res: retrofit2.Response<SearchProductsResponse>
            ) {
                if (!res.isSuccessful) return
                val body = res.body() ?: return
                val products = body.products.map { item ->
                    return@map Product(
                        sourceId = item.id,
                        title = item.title,
                        imageUrl = item.image,
                    )
                }
                callback(products)
            }

            override fun onFailure(call: Call<SearchProductsResponse>, t: Throwable) {
                throw t
            }
        })
    }
}