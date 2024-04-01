package com.example.transitapp


import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.LinearLayout
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
import androidx.wear.compose.material.Text
import com.google.android.libraries.maps.MapView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar (
                actions = {IconButton(onClick = { navController.navigate("MapScreen") }) {
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
                MapScreen()
            }
            composable(route = "RouteScreen") {
                RouteScreen()
            }
            composable(route = "AlertScreen") {
                AlertScreen()
            }
        }
    }
}




@Composable
fun MapScreen() {
    Text(text = "mapscreen")
}

@Composable
fun RouteScreen() {
    Text(text = "RouteScreen")
}

@Composable
fun AlertScreen() {
    Text(text = "AlertScreen")
}

AndroidView(factory = { ctx ->
    val layout = LinearLayout(ctx).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        orientation = LinearLayout.VERTICAL

        // Creating and adding MapView
        val mapView = MapView(ctx).apply {
            onCreate(null)
            getMapAsync { mapboxMap ->
                mapboxMap.setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(-98.0, 39.5))
                        .pitch(0.0)
                        .zoom(2.0)
                        .bearing(0.0)
                        .build()
                )
            }
        }
        addView(mapView)


        val imageView = ImageView(ctx).apply {
            val drawable = ContextCompat.getDrawable(ctx, R.drawable.composelogo)
            setImageDrawable(drawable)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        addView(imageView)
    }
    layout
})