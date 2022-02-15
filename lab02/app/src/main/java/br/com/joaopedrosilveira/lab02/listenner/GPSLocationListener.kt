package br.com.joaopedrosilveira.lab02.listenner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

class GPSLocationListener(
    activity: Activity,
    val onChange: (Location) -> Unit
): LocationListener {
    private val locationManager: LocationManager? =
        activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager?

    init {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1)
        } else {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                2000,
                10.0f,
                this
            )
        }
    }

    override fun onLocationChanged(location: Location) {
        onChange(location)
    }

    override fun onLocationChanged(locations: List<Location?>) {}

    override fun onFlushComplete(requestCode: Int) {}

    @Deprecated("")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String) {}
}