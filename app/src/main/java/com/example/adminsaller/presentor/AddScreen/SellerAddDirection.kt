package com.example.adminsaller.presentor.AddScreen

import com.example.adminsaller.utils.navigation.AppNavigator
import javax.inject.Inject

interface SellerAddDirection {
    suspend fun back()
}

class SellerAddDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SellerAddDirection {


    override suspend fun back() {
        appNavigator.back()
    }
}