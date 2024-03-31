package com.example.mvvm_sample.ui.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mvvm_sample.navigation.BottomNavScreens

@Composable
fun HomeScreen() {
    Text(text = BottomNavScreens.Home.route)
}