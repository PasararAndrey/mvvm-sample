package com.example.mvvm_sample.ui.screen.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mvvm_sample.navigation.BottomNavScreen

@Composable
fun SettingsScreen() {
    Text(text = BottomNavScreen.Settings.route)
}