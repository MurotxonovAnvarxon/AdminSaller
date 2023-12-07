package com.example.adminsaller.data.model

data class ProductsData(
    val productID: String,
    val productName: String,
    val productCount: Int,
    val productInitialPrice: Int,
    val productSellingPrice: Int,
    val productIsValid: Boolean,
    val productComment: String
)