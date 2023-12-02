@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.infinitymoneymanager

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.infinitymoneymanager.ui.CompositionScreen
import com.example.infinitymoneymanager.ui.EvolutionScreen
import com.example.infinitymoneymanager.ui.GoalScreen
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp

sealed class BottomNavItem(var title:String, var icon:ImageVector, var screen_route:String){
    object Composition : BottomNavItem("Composition", Icons.Filled.PieChart,"composition")
    object Evolution: BottomNavItem("Evolution", Icons.Filled.TrendingUp,"evolution")
    object Goal: BottomNavItem("Goal", Icons.Filled.Flag,"goal")
}

@Composable
fun InfinityApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold (
        bottomBar = {BottomNavigation(navController = navController)}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Composition.screen_route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Composition.screen_route) {
                CompositionScreen()
            }
            composable(BottomNavItem.Evolution.screen_route) {
                EvolutionScreen()
            }
            composable(BottomNavItem.Goal.screen_route) {
                GoalScreen()
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Composition,
        BottomNavItem.Evolution,
        BottomNavItem.Goal,
    )
    NavigationBar(
        modifier = Modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title)},
                selected = (currentRoute == item.screen_route),
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
