package com.example.mvvm_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_sample.core.Constants
import com.example.mvvm_sample.navigation.BottomNavScreens
import com.example.mvvm_sample.navigation.graphs.favoriteGraph
import com.example.mvvm_sample.navigation.graphs.homeGraph
import com.example.mvvm_sample.navigation.graphs.settingsGraph
import com.example.mvvm_sample.ui.theme.ComposeEducationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeEducationTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    val bottomNavScreen = BottomNavScreens()

    Scaffold(
        bottomBar = {
            BottomNavBar(bottomNavScreen, selectedItem, navController) { index ->
                selectedItem = index
            }
        }
    ) { innerPadding ->
        NavHost(navController, innerPadding)
    }
}

@Composable
private fun BottomNavBar(
    screens: List<BottomNavScreens>,
    selectedItem: Int,
    navController: NavHostController,
    onItemSelected: (index: Int) -> Unit
) {
    NavigationBar {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
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

@Composable
private fun NavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Constants.NavGraphs.HOME,
        modifier = Modifier.padding(innerPadding)
    ) {
        homeGraph()
        favoriteGraph()
        settingsGraph()
    }
}