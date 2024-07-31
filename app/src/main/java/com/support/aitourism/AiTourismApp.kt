package com.support.aitourism

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AiTourismApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }
}
