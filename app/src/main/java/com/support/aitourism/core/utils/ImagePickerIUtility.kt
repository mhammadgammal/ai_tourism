package com.support.aitourism.core.utils

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun launchPicker(navToShow: (Uri) -> Unit): ManagedActivityResultLauncher<Array<String>, Uri?> {
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { uri ->
            uri?.let {
                navToShow(uri)
            }
        }
    remember { launcher }
    return launcher
}
