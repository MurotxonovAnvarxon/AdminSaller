package com.example.adminsaller.domain.repository

import com.example.adminsaller.data.model.SellerData
import com.example.adminsaller.data.remote.request.SellerRequest
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun addSeller(request: SellerRequest): Flow<Result<String>>
    fun deleteSeller(sellerData: SellerData): Flow<Result<String>>
    fun getSellers(): Flow<Result<List<SellerData>>>

}