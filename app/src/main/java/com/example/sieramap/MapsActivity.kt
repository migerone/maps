package com.example.sieramap

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.sieramap.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    //step3:Getting fused location
    private lateinit var fusedLocationClient:  FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //step 4
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val safaricomHQ = LatLng(-1.2590816841393757, 36.785965161177685)
        mMap.addMarker(MarkerOptions().position(safaricomHQ).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(safaricomHQ,20f))

        val mater = LatLng(-1.304176403916673, 36.833953196679694)
        mMap.addMarker(MarkerOptions().position(mater).title("Marker in mater"))

        val lionplace = LatLng(-1.2600924331333871, 36.79546292332795)
        mMap.addMarker(MarkerOptions().position(lionplace).title("Marker in lionplace"))
//-1.2600924331333871, 36.79546292332795  lionplace
        val parkinn = LatLng(-1.2634175657986917, 36.800398187798706)
        mMap.addMarker(MarkerOptions().position(parkinn).title("Marker in parkinn"))
//-1.2634175657986917, 36.800398187798706 park inn
        val westgate = LatLng(-1.2571251189442632, 36.80319731481198)
        mMap.addMarker(MarkerOptions().position(westgate).title("Marker in westgate"))
//-1.2571251189442632, 36.80319731481198

    gps()

    }//-1.2590816841393757, 36.785965161177685 //-1.304176403916673, 36.833953196679694


    //gps
    fun gps(){
        //step1: permission
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1)
            return

        }//end if

        //step5
        mMap.isMyLocationEnabled = true    //user need to activate GPS to ON on their settings
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
                location ->

            if (location!=null){
                val currentLocation = LatLng(location.latitude, location.longitude)  //this is your current location
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,30f))
            }//end if

            else {
                Toast.makeText(applicationContext, "We can't retrieve your location", Toast.LENGTH_LONG).show()
            }//end else

        }//end fused successlistener


    }

}


