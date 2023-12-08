package com.example.adminsaller.domain.repository

import com.example.adminsaller.data.model.ProductsData
import com.example.adminsaller.data.model.SellerData
import com.example.adminsaller.data.remote.request.ProductRequest
import com.example.adminsaller.data.remote.request.SellerRequest
import kotlinx.coroutines.flow.Flow

interface AppRepository {


    //sellers
    fun addSeller(request: SellerRequest): Flow<Result<SellerData>>
    fun deleteSeller(sellerData: SellerData): Flow<Result<String>>
    fun getSellers(): Flow<Result<List<SellerData>>>
    fun editSeller(id: String, sellerName: String, password: String): Flow<Result<String>>


    //products

    fun addProduct(productRequest: ProductRequest): Flow<Result<ProductsData>>
    fun deleteProduct(productsData: ProductsData): Flow<Result<String>>

    fun getAllProducts(): Flow<List<ProductsData>>

    fun editProducts(
        productsData: ProductsData
    ): Flow<String>



//    fun login(name: String, password: String): Flow<Result<Unit>>

}