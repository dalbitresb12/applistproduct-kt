package com.dalbitresb.applistproduct.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dalbitresb.applistproduct.R
import com.dalbitresb.applistproduct.models.Product
import java.net.URL

class ProductAdapter(private val onClick: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(Comparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_prototype, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ViewHolder(view: View, val onClick: (Product) -> Unit) : RecyclerView.ViewHolder(view) {
        private lateinit var product: Product
        private val imageView: ImageView
        private val nameTextView: TextView
        private val idTextView: TextView
        private val favoriteButton: Button

        init {
            imageView = view.findViewById(R.id.productImageView)
            nameTextView = view.findViewById(R.id.productNameTextView)
            idTextView = view.findViewById(R.id.productIdTextView)
            favoriteButton = view.findViewById(R.id.productFavoriteButton)
        }

        fun bind(product: Product) {
            this.product = product
            nameTextView.text = product.title
            idTextView.text = product.sourceId.toString()
            favoriteButton.setOnClickListener {
                onClick(this.product)
            }
            try {
                val url = URL(product.imageUrl)
                val drawable = Drawable.createFromStream(url.openStream(), "src")
                imageView.setImageDrawable(drawable)
            } catch (_: Exception) {
                // Ignore the error
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.sourceId == newItem.sourceId
                    && oldItem.title == newItem.title
                    && oldItem.imageUrl == newItem.imageUrl
        }
    }
}