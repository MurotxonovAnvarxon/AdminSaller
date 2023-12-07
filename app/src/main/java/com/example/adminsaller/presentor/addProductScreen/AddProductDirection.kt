package com.example.adminsaller.presentor.addProductScreen

import com.example.adminsaller.utils.navigation.AppNavigator
import javax.inject.Inject

interface AddProductDirection {
    suspend fun back()
}


class AddProductsScreenDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : AddProductDirection {
    override suspend fun back() {
        appNavigator.back()
    }

}