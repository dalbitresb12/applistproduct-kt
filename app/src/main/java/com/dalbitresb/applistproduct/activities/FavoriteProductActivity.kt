package com.dalbitresb.applistproduct.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dalbitresb.applistproduct.AppListProductApplication
import com.dalbitresb.applistproduct.R
import com.dalbitresb.applistproduct.adapters.ProductAdapter
import com.google.android.material.appbar.MaterialToolbar

class FavoriteProductActivity : AppCompatActivity() {
    private val productDatabase by lazy { (application as AppListProductApplication).dao }
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_product)
        setupBackButton()
        setupRecyclerView()
    }

    private fun setupBackButton() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarFavoriteProduct)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.favoriteRecyclerView)
        productAdapter = ProductAdapter { product -> productDatabase.delete(product) }
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val products = productDatabase.getAll()
        productAdapter.submitList(products)
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