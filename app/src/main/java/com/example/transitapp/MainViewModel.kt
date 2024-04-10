package com.example.transitapp
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.transit.realtime.GtfsRealtime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.URL

class MainViewModel : ViewModel() {

    private val _feedStateFlow = MutableStateFlow<GtfsRealtime.FeedMessage?>(null)
    val feedStateFlow: StateFlow<GtfsRealtime.FeedMessage?> = _feedStateFlow
    fun loadBusPositions() {
        Thread {
            try {
                val url = URL("https://gtfs.halifax.ca/realtime/Vehicle/VehiclePositions.pb")
                val feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream())

                for (entity in feed.entityList) {
                    if (entity.hasVehicle()) {
                        val vehicle = entity.vehicle
                        val routeName = vehicle.trip.routeId
                        val latitude = vehicle.position.latitude
                        val longitude = vehicle.position.longitude
                        Log.d("BusPositions", "Route: $routeName, Location: ($latitude, $longitude)")
                    }
                }
                
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }.start()
    }

    private fun fetchFeed() {
        // Fetch feed from URL
        val url = URL("https://gtfs.halifax.ca/realtime/Vehicle/VehiclePositions.pb")
        val feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream())

        // Update StateFlow with fetched feed
        _feedStateFlow.value = feed
    }
    init {
        fetchFeed()
    }

}