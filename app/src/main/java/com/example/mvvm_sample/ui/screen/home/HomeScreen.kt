package com.example.mvvm_sample.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvm_sample.navigation.BottomNavScreens
import com.example.mvvm_sample.ui.screen.settings.SettingsScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = BottomNavScreens.Home.route)
    }
}


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    HomeScreen()
}