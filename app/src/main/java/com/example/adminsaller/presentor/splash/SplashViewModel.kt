package com.example.adminsaller.presentor.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminsaller.presentor.splash.SplashDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashDirection: SplashDirection
):ViewModel() {
    init {
        viewModelScope.launch {
            delay(1500L)
            splashDirection.moveToSellersScreen()
        }
    }
}