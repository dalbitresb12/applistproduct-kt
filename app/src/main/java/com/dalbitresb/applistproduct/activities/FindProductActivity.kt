package com.dalbitresb.applistproduct.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dalbitresb.applistproduct.AppListProductApplication
import com.dalbitresb.applistproduct.R
import com.dalbitresb.applistproduct.adapters.ProductAdapter
import com.dalbitresb.applistproduct.fetchers.ProductFetcher
import com.google.android.material.appbar.MaterialToolbar

class FindProductActivity : AppCompatActivity() {
    private val productFetcher =
        ProductFetcher("https://api.spoonacular.com/", "6ac7bc84658d4529b01b0d61557f4551")
    private val productDatabase by lazy { (application as AppListProductApplication).dao }
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_product)
        setupBackButton()
        setupRecyclerView()
        setupSearch()
    }

    private fun setupBackButton() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarFindProduct)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.productRecyclerView)
        productAdapter = ProductAdapter { product -> productDatabase.insert(product) }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle arrow click
        if (item.itemId == android.R.id.home) {
            // Close this activity and go to the previous activity (if any)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}