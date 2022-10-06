package com.dalbitresb.applistproduct

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dalbitresb.applistproduct.activities.FavoriteProductActivity
import com.dalbitresb.applistproduct.activities.FindProductActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFindProduct()
        setupFavoriteProduct()
    }

    private fun setupFindProduct() {
        val button = findViewById<Button>(R.id.findProductButton)
        button.setOnClickListener {
            val intent = Intent(this, FindProductActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupFavoriteProduct() {
        val button = findViewById<Button>(R.id.favoriteProductsButton)
        button.setOnClickListener {
            val intent = Intent(this, FavoriteProductActivity::class.java)
            startActivity(intent)
        }
    }
}