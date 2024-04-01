package com.example.transitapp

import androidx.lifecycle.ViewModel
import com.google.transit.realtime.GtfsRealtime
import java.net.URL

class MainViewModel : ViewModel() {
    fun loadBusPositions() {
        Thread {
            try {
                val url = URL("https://gtfs.halifax.ca/realtime/Vehicle/VehiclePositions.pb")
                val feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream())


            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }.start()
    }
}