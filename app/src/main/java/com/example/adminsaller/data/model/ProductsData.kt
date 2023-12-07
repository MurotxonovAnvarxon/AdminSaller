package com.example.adminsaller.data.model

import com.google.firebase.database.DataSnapshot

data class ProductsData(
    val productID: String,
    val productName: String,
    val productCount: Int,
    val productInitialPrice: Int,
    val productSellingPrice: Int,
    val productIsValid: Boolean,
    val productComment: String
)

fun DataSnapshot.toCommonData(): ProductsData = ProductsData(
    productID = child("productID").getValue(String::class.java) ?: "",
    productName = child("productName").getValue(String::class.java) ?: "",
    productCount = child("productCount").getValue(Int::class.java) ?: 0,
    productInitialPrice = child("productInitialPrice").getValue(Int::class.java) ?: 0,
    productSellingPrice = child("productSellingPrice").getValue(Int::class.java) ?: 0,
    productIsValid = child("productIsValid").getValue(Boolean::class.java) ?: false,
    productComment = child("productComment").getValue(String::class.java) ?: ""
)