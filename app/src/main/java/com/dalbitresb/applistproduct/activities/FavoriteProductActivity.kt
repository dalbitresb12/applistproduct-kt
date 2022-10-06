package com.dalbitresb.applistproduct.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dalbitresb.applistproduct.R
import com.google.android.material.appbar.MaterialToolbar

class FavoriteProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_product)
        setupBackButton()
    }

    private fun setupBackButton() {
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarFavoriteProduct)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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