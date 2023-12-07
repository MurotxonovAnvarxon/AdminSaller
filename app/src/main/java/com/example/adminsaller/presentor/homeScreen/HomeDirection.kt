package com.example.adminsaller.presentor.homeScreen

import com.example.adminsaller.presentor.AddScreen.AddScreen
import com.example.adminsaller.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface HomeDirection {
    suspend fun moveToAddScreen()
    suspend fun moveToProductScreen()
}

@Singleton
class HomeDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeDirection {
    override suspend fun moveToAddScreen() {
        appNavigator.addScreen(AddScreen())
    }

    override suspend fun moveToProductScreen() {
//        appNavigator.addScreen(ProductScreen())
    }



}