package com.example.mvvm_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_sample.core.Constants
import com.example.mvvm_sample.navigation.BottomNavScreens
import com.example.mvvm_sample.ui.screen.home.HomeScreen
import com.example.mvvm_sample.ui.theme.ComposeEducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeEducationTheme {
                val navController = rememberNavController()
                var selectedItem by remember {
                    mutableIntStateOf(0)
                }
                val items = BottomNavScreens()
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItem == index,
                                    onClick = {
                                        selectedItem = index
                                        navController.navigate(item.route) {
                                            popUpTo(
                                                navController.graph.findStartDestination().id
                                            ) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = null
                                        )
                                    })
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Constants.NavGraphs.HOME,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        navigation(
                            startDestination = BottomNavScreens.Home.route,
                            route = Constants.NavGraphs.HOME
                        ) {
                            composable(route = BottomNavScreens.Home.route) {
                                HomeScreen()
                            }
                        }

                        navigation(
                            startDestination = BottomNavScreens.Favorite.route,
                            route = Constants.NavGraphs.FAVORITE
                        ) {
                            composable(route = BottomNavScreens.Favorite.route) {
                                BottomNavScreens.Favorite.screen()
                            }
                        }

                        navigation(
                            startDestination = BottomNavScreens.Settings.route,
                            route = Constants.NavGraphs.SETTINGS
                        ) {
                            composable(route = BottomNavScreens.Settings.route) {
                                BottomNavScreens.Settings.screen()
                            }
                        }
                    }
                }
            }
        }
    }
}