package com.example.transitapp


import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions


@Composable
fun MapScreen(mainViewModel : MainViewModel) {

    val busFeed by mainViewModel.feedStateFlow.collectAsState()

    AndroidView(
        update = { mapView ->

        },
        factory = { ctx ->
            val  mapView = MapView(ctx)
            mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .center(Point.fromLngLat(-63.592514, 44.650813))
                .pitch(0.0)
                .zoom(12.0)
                .bearing(0.0)
                .build()
            )
            val viewAnnotationManager = mapView.viewAnnotationManager

            if (busFeed  != null) {
                for (entity in busFeed?.entityList!!) {
                    if (entity.hasVehicle()) {
                        val vehicle = entity.vehicle
                        val routeName = vehicle.trip.routeId
                        val latitude = vehicle.position.latitude
                        val longitude = vehicle.position.longitude
                        val viewAnnotation = viewAnnotationManager.addViewAnnotation(
                            // Specify the layout resource id

                            resId = R.layout.layout,
                            // Set any view annotation options
                            options = viewAnnotationOptions {
                                // View annotation is placed at the specific geo coordinate
                                geometry(Point.fromLngLat(longitude.toDouble(), latitude.toDouble()))
                            })
                        val annotationTxtView = viewAnnotation.findViewById<TextView>(androidx.constraintlayout.widget.R.id.layout)
                        annotationTxtView.text = routeName

                    }
                }
            }



//        mainViewModel.feedStateFlow.observe(lifecycleOwner) { feedMessage ->
//
//            feedMessage?.let {
//
//                for (entity in it.entityList) {
//                    val vehicle = entity.vehicle
//                    val routeId = vehicle.trip.routeId
//                    val latitude = vehicle.position.latitude
//                    val longitude = vehicle.position.longitude
//
//
//                    Log.d("BusData", "Route ID: $routeId, Latitude: $latitude, Longitude: $longitude")
//
//
//                    mapboxMap.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title("Bus $routeId"))
//                }
//            }
//        }
//    }
//}

    mapView.apply {  }

    })

}

