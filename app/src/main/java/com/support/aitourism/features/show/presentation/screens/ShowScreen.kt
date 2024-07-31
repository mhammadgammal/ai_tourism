package com.support.aitourism.features.show.presentation.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ShowScreen(uri: String?) {
    Log.d(TAG, "ShowScreen: Uri in ShowScreen: $uri")

    AsyncImage(
        model = uri,
        contentDescription = null,
        modifier = Modifier
            .size(200.dp),
        contentScale = ContentScale.Crop
    )
}

const val TAG = "ShowScreen"
