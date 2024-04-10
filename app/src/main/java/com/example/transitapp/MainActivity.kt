package com.example.transitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = MainViewModel()

        mainViewModel.loadBusPositions()

        setContent {
                App()

            }
        }
    @Composable
    fun App() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomAppBar (
                    actions = {
                        IconButton(onClick = { navController.navigate("MapScreen") }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_map_24),
                                contentDescription = "MapScreen"
                            )
                        }
                        IconButton(onClick = { navController.navigate("RouteScreen") }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_route_24),
                                contentDescription = "RouteScreen"
                            )
                        }
                        IconButton(onClick = { navController.navigate("AlertScreen") }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_add_alert_24),
                                contentDescription = "AlertScreen"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "MapScreen",
                modifier = Modifier.padding(innerPadding)
            ){
                composable(route = "MapScreen") {
                    MapScreen(mainViewModel)

                }
                composable(route = "RouteScreen") {
                    RouteScreen(mainViewModel)
                }
                composable(route = "AlertScreen") {
                    AlertScreen(mainViewModel)
                }
            }
        }
    }
    }

