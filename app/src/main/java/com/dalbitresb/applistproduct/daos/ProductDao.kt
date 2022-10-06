package com.dalbitresb.applistproduct.daos

import androidx.room.*
import com.dalbitresb.applistproduct.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)
}