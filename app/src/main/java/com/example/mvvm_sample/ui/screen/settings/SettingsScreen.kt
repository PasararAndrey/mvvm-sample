package com.example.mvvm_sample.ui.screen.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mvvm_sample.navigation.BottomNavScreens

@Composable
fun SettingsScreen() {
    Text(text = BottomNavScreens.Settings.route)
}