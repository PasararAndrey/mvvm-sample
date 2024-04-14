package com.example.mvvmsample.navigation.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.mvvmsample.navigation.destinations.NavDestinations

fun NavController.navigateToBookDetails(
    arg: String,
    navOptions: NavOptions? = null,
) {
    this.navigate("${NavDestinations.Books.route}/$arg", navOptions)
}

fun NavController.navigateBackToBooks(arg: String) {
    this.navigateBackWithResult(NavDestinations.Books.ARGUMENT_KEY, arg)
}

fun <T> NavController.navigateBackWithResult(
    key: String,
    value: T?,
) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
    popBackStack()
}
