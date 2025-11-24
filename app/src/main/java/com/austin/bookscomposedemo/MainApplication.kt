package com.austin.bookscomposedemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    // Only provided for Hilt as of now
}