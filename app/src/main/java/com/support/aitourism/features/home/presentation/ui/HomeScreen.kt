package com.support.aitourism.features.home.presentation.ui

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.support.aitourism.core.composables.CustomButton

@Composable
fun HomeScreen(
    openCamera: () -> Unit,
    openGallery: @Composable () -> ManagedActivityResultLauncher<Array<String>, Uri?>
) {
    val galleryLauncher = openGallery()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomButton(
            onClick = {
                openCamera()
            }) {
            Text(text = "Open Camera")
        }
        CustomButton(
            onClick = { galleryLauncher.launch(arrayOf("image/*")) }) {
            Text(text = "Select from Your Gallery")
        }
    }
}
