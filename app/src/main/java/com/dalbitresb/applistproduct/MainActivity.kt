package com.dalbitresb.applistproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dalbitresb.applistproduct.activities.FindProductActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFindProduct()
    }

    private fun setupFindProduct() {
        val button = findViewById<Button>(R.id.findProductButton)
        button.setOnClickListener {
            val intent = Intent(this, FindProductActivity::class.java)
            startActivity(intent)
        }
    }
}