@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.infinitymoneymanager

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.infinitymoneymanager.ui.AddTransactionScreen
import com.example.infinitymoneymanager.ui.transactions

//Defining the screens for navigation
sealed class BottomNavItem(var title:String, var icon:ImageVector, var screen_route:String){
    object Composition : BottomNavItem("Composition", Icons.Filled.PieChart,"composition")
    object Evolution: BottomNavItem("Evolution", Icons.Filled.TrendingUp,"evolution")
    object Goal: BottomNavItem("Goal", Icons.Filled.Flag,"goal")
}
@Composable
fun InfinityApp(
    navController: NavHostController = rememberNavController()
) {
    val bottomNavigationController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "finance_screen",
        modifier = Modifier
    ) {
        composable("finance_screen"){
            FinanceScreen(
                navController = navController,
                bottomNavigationController = bottomNavigationController
            )
        }
        composable("add_transaction_screen"){
            AddTransactionScreen(
                navController = navController
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfinityAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}

// Main app
@Composable
fun FinanceScreen(
    navController: NavHostController,
    bottomNavigationController: NavHostController,
) {
    Scaffold (
        topBar = { InfinityAppBar() },
        bottomBar = {BottomNavigation(navController = bottomNavigationController)}
    ) { innerPadding ->
        NavHost(
            navController = bottomNavigationController,
            startDestination = BottomNavItem.Composition.screen_route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Composition.screen_route) {
                CompositionScreen(
                    transactions = transactions,
                    navController = navController
                )
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
    ) {
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->

            val iconColor = if (currentRoute == item.screen_route) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.primary

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = iconColor
                    )},
                label = {
                    Text(
                        text = item.title,
                        color = MaterialTheme.colorScheme.primary
                    )},
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
