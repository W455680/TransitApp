package com.example.transitapp


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView


@Composable
fun MapScreen(mainViewModel : MainViewModel) {

    AndroidView(factory = { ctx ->
    val  mapView = MapView(ctx)
    mapView.mapboxMap.setCamera(
        CameraOptions.Builder()
            .center(Point.fromLngLat(-63.592514, 44.650813))
            .pitch(0.0)
            .zoom(12.0)
            .bearing(0.0)
            .build()
    )

        mainViewModel.feedStateFlow.observe(lifecycleOwner) { feedMessage ->

            feedMessage?.let {

                for (entity in it.entityList) {
                    val vehicle = entity.vehicle
                    val routeId = vehicle.trip.routeId
                    val latitude = vehicle.position.latitude
                    val longitude = vehicle.position.longitude


                    Log.d("BusData", "Route ID: $routeId, Latitude: $latitude, Longitude: $longitude")

                    // Add MapBox marker to display bus on the map
                    mapboxMap.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title("Bus $routeId"))
                }
            }
        }
    }
}

    mapView.apply {  }

    })

}

