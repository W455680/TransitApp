package com.example.transitapp

import androidx.compose.runtime.Composable


@Composable
fun AlertScreen(mainViewModel : MainViewModel) {

    //val alerts by mainViewModel.feedTwoStateFlow.collectAsState()


//    val _alerts = MutableStateFlow<List<GtfsRealtime.Alert>>(emptyList())
//    val alerts: StateFlow<List<GtfsRealtime.Alert>> = _alerts
//
//    init {
//        fetchAlerts()
//    }
//
//
//    val alerts = mainViewModel.alerts.collectAsState(initial = emptyList()).value
//    Surface(color = MaterialTheme.colors.background) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Halifax Transit Alerts", style = MaterialTheme.typography.h5)
//            if (alerts.isNotEmpty()) {
//                alerts.forEach { alert ->
//                    AlertItem(alert = alert)
//                }
//            } else {
//                Text(text = "Failed to fetch alerts data!", style = MaterialTheme.typography.body1)
//            }
//        }
//    }
}
//
//@Composable
