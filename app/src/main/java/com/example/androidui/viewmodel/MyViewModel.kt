package com.example.androidui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class MyViewModel : ViewModel() {
    // State is now kept in ViewModel
    var surfaceColorInt by mutableStateOf(Color.White.toArgb())
        private set // Make the setter private to control state changes

    // Function to toggle the color
    fun toggleColor() {
        surfaceColorInt = if (surfaceColorInt == Color.Red.toArgb()) {
            Color.Blue.toArgb()
        } else {
            Color.Red.toArgb()
        }
    }

    // Function to reset the color
    fun resetColor() {
        surfaceColorInt = Color.White.toArgb()
    }
}
