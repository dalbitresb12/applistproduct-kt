package com.dalbitresb.applistproduct.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dalbitresb.applistproduct.R
import com.dalbitresb.applistproduct.adapters.ProductAdapter
import com.dalbitresb.applistproduct.fetchers.ProductFetcher

class FindProductActivity : AppCompatActivity() {
    private val productFetcher =
        ProductFetcher("https://api.spoonacular.com/", "6ac7bc84658d4529b01b0d61557f4551")
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_product)
        setupRecyclerView()
        setupSearch()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.productRecyclerView)
        productAdapter = ProductAdapter()
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupSearch() {
        val editText = findViewById<EditText>(R.id.productNameEditText)
        val button = findViewById<Button>(R.id.productSearchButton)
        button.setOnClickListener {
            val query = editText.text.toString()
            productFetcher.searchProducts(query, callback = { products ->
                productAdapter.submitList(products)
            })
        }
    }
}