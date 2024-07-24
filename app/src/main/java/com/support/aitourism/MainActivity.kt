package com.support.aitourism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.*
import com.support.aitourism.features.authentication.presentation.ui.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
//        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable("login", content = {
                LoginScreen()
            })
        }
    }
}
