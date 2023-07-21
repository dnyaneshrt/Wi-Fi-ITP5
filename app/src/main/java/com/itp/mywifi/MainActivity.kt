package com.itp.mywifi


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var wifiManager:WifiManager
    var s_wifi: Switch?=null
    var btn1:Button?=null
    var btn2:Button?=null
    var listview:ListView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s_wifi=findViewById(R.id.s_wifi)
        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        listview=findViewById(R.id.listview)


        var status =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (status == PackageManager.PERMISSION_GRANTED) {
            getWifi()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),
                10
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED||grantResults[1]==PackageManager.PERMISSION_GRANTED){
            getWifi()
            Toast.makeText(this, "location serive", Toast.LENGTH_LONG).show()

        } else {

            Toast.makeText(this, "user is not allowed here", Toast.LENGTH_LONG).show()

        }
    }

    private fun getWifi() {

        //step 1

      wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        //getWifiState method is used t get the state of wifi
        var state = wifiManager.wifiState

        s_wifi!!.isChecked =
            state == WifiManager.WIFI_STATE_ENABLED || state == WifiManager.WIFI_STATE_ENABLING

        //isWifiEbnabled method used to change the state of your wifi
        s_wifi!!.setOnCheckedChangeListener { buttonView, isChecked ->
            wifiManager.isWifiEnabled = isChecked
        }

        btn1?.setOnClickListener {

            var status =
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

            if (status == PackageManager.PERMISSION_GRANTED) {
                var results = wifiManager.scanResults
                var list_results = mutableListOf<String>()
                for (result in results) {
                    list_results.add(result.SSID + " -- " + result.frequency)
                }
                var adpater =
                    ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list_results)
                listview?.adapter = adpater
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),
                    10
                )
            }
        }

        btn2?.setOnClickListener{

            var results=wifiManager.configuredNetworks
            var list_results= mutableListOf<String>()
            for(result in results)
            {
                var status=""
                if(result.status==0)
                {
                    status="connected "
                }else if(result.status==1)
                {
                    status="network available"
                }else if(result.status==2)
                {
                    status="network unavaiable"
                }
                list_results.add(result.SSID+" -------- "+status)
            }
            var adpater =
                ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list_results)
            listview?.adapter = adpater
        }
    }

}