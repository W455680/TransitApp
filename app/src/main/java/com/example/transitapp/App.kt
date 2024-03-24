package com.example.transitapp


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import androidx.compose.ui.graphics.vector.ImageVector

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigation {
                    val screens = listOf(
                        Screen.Map,
                        Screen.Route,
                        Screen.Alert
                    )
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    screens.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                            label = { Text(text = stringResource(screen.title)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screen.Map.route) {
            composable(Screen.Map.route) {
                MapScreen()
            }
            composable(Screen.Route.route) {
                RouteScreen()
            }
            composable(Screen.Alert.route) {
                AlertScreen()
            }
        }
    }
}

sealed class Screen(val route: String, val title: Int, val icon: ImageVector) {
    object Map : Screen("map", R.string.map_screen, Icons.Default.map)
    object Route : Screen("route", R.string.route_screen, Icons.Default.directions)
    object Alert : Screen("alert", R.string.alert_screen, Icons.Default.notifications)
}

@Composable
fun MapScreen() {
    ScreenContent(Screen.Map.title)
}

@Composable
fun RouteScreen() {
    ScreenContent(Screen.Route.title)
}

@Composable
fun AlertScreen() {
    ScreenContent(Screen.Alert.title)
}

@Composable
fun ScreenContent(title: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = title))
    }
}