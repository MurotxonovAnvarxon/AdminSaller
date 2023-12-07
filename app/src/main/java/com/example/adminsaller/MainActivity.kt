package com.example.adminsaller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.adminsaller.ui.theme.AdminSallerTheme
import com.example.adminsaller.utils.navigation.AppNavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var handler: AppNavigationHandler
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContent {
            AdminSallerTheme {
                Navigator(screen = com.example.adminsaller.presentor.splash.SplashScreen()) { navigate ->
                    handler.uiNavigator
                        .onEach { it.invoke(navigate) }
                        .launchIn(lifecycleScope)
                    CurrentScreen()
                }

            }
        }
    }
}

