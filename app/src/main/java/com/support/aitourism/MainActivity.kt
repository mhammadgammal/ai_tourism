package com.support.aitourism

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.support.aitourism.core.utils.BaseActivity
import com.support.aitourism.core.utils.launchPicker
import com.support.aitourism.features.authentication.presentation.ui.login.LoginScreen
import com.support.aitourism.features.home.presentation.ui.HomeScreen
import com.support.aitourism.features.show.presentation.screens.ShowScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : BaseActivity() {
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
        NavHost(navController = navController, startDestination = "home") {
            composable("login", content = {
                LoginScreen {
                    navController.navigate("home")
                }
            })

            composable("home", content = {
                HomeScreen(openCamera = {
                    handleCameraPermission {
                        Log.d(TAG, "Navigation from camera: $it")

                        navController.navigate(ShowImage(uri = it))
                    }
                }, openGallery = {
                    return@HomeScreen launchPicker {
                        Log.d(TAG, "Navigation from picker: $it")

                        navController.navigate(ShowImage(uri = it.toString()))
                    }
                })
            })

            composable<ShowImage> {
                val showImage = it.toRoute<ShowImage>()
                ShowScreen(uri = showImage.uri)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

@Serializable
data class ShowImage(val uri: String)
