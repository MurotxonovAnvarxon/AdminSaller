package com.example.adminsaller.data.model

data class ProductsData(
    val id: String,
    val name: String,
    val count: Int,
    val initialPrice: Int,
    val sellingPrice: Int,
    val isValid: Boolean,
    val comment: String
)