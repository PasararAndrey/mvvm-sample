package com.example.mvvmsample.ui.app

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.mvvmsample.navigation.BottomNavScreens

@Composable
fun BottomNavBar(
    screens: List<BottomNavScreens>,
    selectedItem: Int,
    navController: NavHostController,
    onItemSelected: (index: Int) -> Unit,
) {
    NavigationBar {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                    navController.navigate(item.route) {
                        popUpTo(
                            navController.graph.findStartDestination().id,
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
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
