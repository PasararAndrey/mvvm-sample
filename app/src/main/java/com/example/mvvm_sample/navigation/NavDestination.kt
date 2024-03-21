package com.example.mvvm_sample.navigation

import androidx.compose.runtime.Composable

interface NavDestination {
    val route: String
    val screen: @Composable () -> Unit
}

