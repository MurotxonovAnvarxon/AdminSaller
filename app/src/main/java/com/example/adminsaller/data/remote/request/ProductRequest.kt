package com.example.adminsaller.data.remote.request

data class ProductRequest(
    val productID: String,
    val productName: String,
    val productCount: Int,
    val productInitialPrice: Int,
    val productSellingPrice: Int,
    val productIsValid: Boolean,
    val productComment: String
)