package com.example.mvvmsample.navigation.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateToFavoriteSelected(
    arg: String,
    navOptions: NavOptions? = null,
) {
    this.navigate("${MainBottomNavDestinations.Favorite.route}/$arg", navOptions)
}

fun NavController.navigateBackToFavoriteFromSelectedFavorite(arg: String) {
    this.navigateBackWithResult(MainBottomNavDestinations.Favorite.ARGUMENT_KEY, arg)
}

fun <T> NavController.navigateBackWithResult(
    key: String,
    value: T?,
) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
    popBackStack()
}
