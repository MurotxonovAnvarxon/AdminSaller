package com.example.adminsaller.presentor.productScreen

import com.example.adminsaller.presentor.addProductScreen.AddProductScreen
import com.example.adminsaller.presentor.draft.DraftScreen
import com.example.adminsaller.utils.navigation.AppNavigator
import javax.inject.Inject

interface ProductDirection {
    suspend fun moveToAddProductScreen()
    suspend fun moveToDraft()
    suspend fun back()
}


class ProductDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ProductDirection {
    override suspend fun moveToAddProductScreen() {
        appNavigator.addScreen(AddProductScreen())
    }

    override suspend fun moveToDraft() {
        appNavigator.addScreen(DraftScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }

}