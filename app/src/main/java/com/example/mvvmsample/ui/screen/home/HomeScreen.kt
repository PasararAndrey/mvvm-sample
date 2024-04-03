package com.example.mvvmsample.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmsample.navigation.BottomNavScreens

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
