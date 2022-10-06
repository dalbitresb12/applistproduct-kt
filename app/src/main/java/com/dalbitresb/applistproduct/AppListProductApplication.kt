package com.dalbitresb.applistproduct

import android.app.Application

class AppListProductApplication : Application() {
    private val database by lazy { ProductDatabase.getInstance(this) }
    val dao by lazy { database.productDao() }
}