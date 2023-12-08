package com.example.adminsaller.presentor.splash

import com.example.adminsaller.presentor.homeScreen.HomeScreen
import com.example.adminsaller.presentor.login.LoginScreen
import com.example.adminsaller.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface SplashDirection {
    suspend fun moveToSellersScreen()
}

@Singleton
class SplashDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
): SplashDirection {
    override suspend fun moveToSellersScreen() {
        appNavigator.replaceScreen(LoginScreen())
    }

}