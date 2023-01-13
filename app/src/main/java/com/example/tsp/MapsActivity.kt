package com.example.tsp

import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tsp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        val test : ArrayList<Int> = intent.getIntegerArrayListExtra("cities") as ArrayList<Int>





        val eilTsp = TSP("assets/distance.txt",  100000);
        val ga = GA(100, 0.8, 0.1)

        val bestPath = ga.execute(eilTsp, test)
        mMap = googleMap
        for (arr in eilTsp.weights) {
            for (s in arr) {
                println(s)
            }
        }

        println(bestPath.distance)
        for(i in 0..bestPath.dimension - 1) {
            print(bestPath.getPath()[i].index)
        }
        println()

        val koordinate = arrayOf(
            "46.0507279 14.5150894",
            "46.056917 14.506565",
            "46.0563371 14.5346821",
            "46.0802363 14.5123903",
            "46.0391767 14.4993982",
            "46.0786334 14.472417457509469",
            "46.0543632 14.5110433",
            "46.0904385 14.4724879",
            "46.2027467 14.5416029",
            "46.102452400000004 14.529604946970263",
            "46.16293695 14.568635599999997",
            "46.2071371 14.5972992",
            "46.1354999 14.744729142294661",
            "46.0560413 14.5842302",
            "45.956227299999995 14.658029655931532",
            "45.938337 14.805902",
            "45.7400169 14.727240997933432",
            "45.6484714 14.8541151",
            "46.02046355 14.415704107227107",
            "45.9180558 14.3650811",
            "45.9632084 14.2957835",
            "46.1305084 14.996442141104732",
            "46.1481735 15.0419847",
            "46.1605268 15.0535934",
            "46.54850635 15.674328746700834",
            "46.551423650000004 15.621242846934372",
            "46.6968585 15.745341",
            "46.5556197 15.693498305658114",
            "46.5773462 15.830624155714286",
            "46.6070732 15.8914134",
            "46.50056385 15.749516986904126",
            "46.416585 15.85988435378695",
            "46.4623418 16.1572111",
            "46.4079472 16.1487501",
            "46.40860605 16.01265850463508",
            "46.3691918 15.9022976",
            "46.4238499 15.60661",
            "46.3173932 15.666732200000004",
            "46.4034488 15.797030429161648",
            "46.453489250000004 15.674746399999997",
            "46.5725315 15.6119566",
            "46.55151975 15.49303763874046",
            "46.5670972 15.5783431",
            "46.6100287 15.1633537",
            "46.589099899999994 15.024276898644445",
            "46.5447781 14.9661684",
            "46.5453396 14.9134678",
            "46.4685159 14.8450257",
            "46.229444 15.2673215",
            "46.22866105 15.518382299999999",
            "46.2526883 15.1599574",
            "46.279713 15.077103407402827",
            "46.3388057 14.9601755",
            "46.24859815 14.356533528222306",
            "46.23104265 14.3462575",
            "46.2471152 14.3688525",
            "46.2376486 14.3654931",
            "46.2729214 14.315677979006164",
            "46.242359300000004 14.419066104205832",
            "46.16687005 14.305875117582715",
            "46.0479076 14.1098615",
            "46.2260935 14.1656719",
            "46.3612909 14.1582159",
            "46.2723057 13.9529801",
            "46.4396674 14.0431733",
            "46.4051083 14.1335494",
            "46.4849141 13.7812559",
            "46.3619899 14.3082064",
            "45.9555521 13.6481003",
            "45.9542876 13.6738601",
            "46.1831542 13.733412",
            "45.8872446 13.9031492",
            "46.001312 14.0261357",
            "45.9284121 13.6405516",
            "45.5417991 13.7353379",
            "45.70750865 13.868569948911155",
            "45.774275849999995 14.213825861786919",
            "45.537082 13.660708513722671",
            "45.798706 15.1556646",
            "45.8048891 15.1653739",
            "45.907541800000004 15.007276283229231",
            "45.9571244 15.0560988",
            "45.9048672 15.593317664714808",
            "45.8957693 15.6434467",
            "45.8558296 15.681843",
            "45.95761475 15.494711023437496",
            "46.0097309 15.3029651",
            "45.8449113 15.3439764",
            "45.644933949999995 15.316766349817636",
            "45.5747806 15.19055235539216",
            "46.660739199999995 16.16377555213085",
            "46.8030761 16.2176988",
            "46.5617296 16.4549466",
            "46.5691383 16.29363244905143",
            "46.67515095 15.994154774905388",
            "46.642584 16.0393224"
        )

        val naslovi = arrayOf(
            "Poljanski nasip 30",
            "Pražakova ulica 3",
            "Zaloška cesta 57",
            "Dunajska cesta 141",
            "Riharjeva ulica 38",
            "Vodnikova cesta 235",
            "Resljeva cesta 14",
            "Prušnikova ulica 2",
            "Glavarjeva cesta 39",
            "Dunajska cesta 361",
            "Trdinov trg 8 A",
            "Ljubljanska cesta 14 A",
            "Vegova ulica 1",
            "Zadobrovška cesta 14",
            "Partizanska cesta 7",
            "Ploščad Osvobodilne fronte 4",
            "Kolodvorska ulica 2",
            "Ljubljanska cesta 23",
            "Podpeška cesta 20",
            "Molkov trg 12 ",
            "Poštna ulica 2",
            "Cesta zmage 28",
            "Trg revolucije 27",
            "Trg Franca Fakina 4",
            "Istrska ulica 49",
            "Šarhova ulica 59 A",
            "Sladki Vrh 3 A",
            "Malečnik 56",
            "Partizanska cesta 3",
            "Čolnikov trg 9",
            "Cesta k Dravi 5",
            "Mariborska Cesta 19",
            "Ivanjkovci 9 B",
            "Poštna ulica 2",
            "Gorišnica 79",
            "Videm pri Ptuju 51 A",
            "Mariborska ulica 26",
            "Makole 37",
            "Kopališka ulica 2",
            "Ljubljanska cesta 14",
            "Cesta v Rošpoh 18",
            "Slovenski trg 4",
            "Obrobna ulica 1",
            "Glavni trg 31",
            "Trg 4. julija 1",
            "Trg svobode 19",
            "Trg 32 A",
            "Center 16",
            "Krekov trg 9",
            "Aškerčev trg 26",
            "Ulica heroja Staneta 1",
            "Malteška cesta 38",
            "Savinjska cesta 3",
            "Dražgoška ulica 8",
            "Škofjeloška cesta 17",
            "Jezerska cesta 41",
            "Ulica Lojzeta Hrovata 2",
            "Glavna cesta 24",
            "Gasilska cesta 2 A",
            "Kapucinski trg 14",
            "Trg svobode 1",
            "Na Kresu 1",
            "Alpska cesta 37 B",
            "Trg svobode 2 C",
            "Cesta Cirila Tavčarja 8",
            "Žirovnica 4 ",
            "Borovška cesta 92",
            "Predilniška cesta 10",
            "Kidričeva ulica 19",
            "Industrijska cesta 9",
            "Trg maršala Tita 10",
            "Goriška cesta 24",
            "Vodnikova ulica 1",
            "Vrtojbenska cesta 19 C",
            "Kolodvorska cesta 9",
            "Partizanska cesta 48 A",
            "Ulica 1. maja 2 A",
            "Cankarjev drevored 1",
            "Ulica Slavka Gruma 7",
            "Novi trg 7",
            "Goliev trg 11",
            "Cesta na Fužine 3",
            "Ulica stare pravde 34",
            "Ulica bratov Gerjovičev 52",
            "Jesenice 9",
            "Trg Matije Gubca 1",
            "Trg svobode 9",
            "Prvomajska cesta 3",
            "Naselje Borisa Kidriča 2",
            "Kolodvorska cesta 30 A",
            "Trg Zmage 6",
            "Gornji Petrovci 40 E",
            "Trg ljudske pravice 7",
            "Ulica Prekmurske čete 14",
            "Cesta na Stadion 1",
            "Panonska cesta 5"
        )

        val line = koordinate[bestPath.getPath()[0].index]
        val x = line.split(" ")[0].toDouble()
        val y = line.split(" ")[1].toDouble()
        val firstPos = LatLng(x, y)

        // Add a marker in Sydney and move the camera
        for (i in 0 .. bestPath.dimension - 2) {
            val line1 = koordinate[bestPath.getPath()[i].index]
            val x1 = line1.split(" ")[0].toDouble()
            val y1 = line1.split(" ")[1].toDouble()
            val line2 = koordinate[bestPath.getPath()[i+1].index]
            val x2 = line2.split(" ")[0].toDouble()
            val y2 = line2.split(" ")[1].toDouble()
            val sydney1 = LatLng(x1, y1)
            val sydney2 = LatLng(x2, y2)
            mMap.addMarker(MarkerOptions().position(sydney1).title("[" + i.toString() + "] " + naslovi[bestPath.getPath()[i].index]))
            mMap.addMarker(MarkerOptions().position(sydney2).title("[" + (i + 1).toString() + "] " + naslovi[bestPath.getPath()[i + 1].index]))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPos, 13f))


            var URL = getDirectionURL(sydney1,sydney2)
            GetDirection(URL).execute()
        }
    }

    fun getDirectionURL(origin: LatLng, dest: LatLng): String {
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}&destination=${dest.latitude},${dest.longitude}&sensor=false&mode=driving&key=AIzaSyDLZIwPB8zpEmFD9eIrcXAZ_GBVygwFOzQ"
    }

    private inner class GetDirection(val url : String) : AsyncTask<Void, Void, List<List<LatLng>>>(){
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val data = response.body()!!.string()
            Log.d("GoogleMap" , " data : $data")
            val result =  ArrayList<List<LatLng>>()
            try{
                val respObj = Gson().fromJson(data,GoogleMapDTO::class.java)

                val path =  ArrayList<LatLng>()

                for (i in 0..(respObj.routes[0].legs[0].steps.size-1)){
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            }catch (e:Exception){
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: List<List<LatLng>>) {
            val lineoption = PolylineOptions()
            for (i in result.indices){
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.color(Color.BLUE)
                lineoption.geodesic(true)
            }
            mMap.addPolyline(lineoption)
        }
    }

    public fun decodePolyline(encoded: String): List<LatLng> {

        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val latLng = LatLng((lat.toDouble() / 1E5),(lng.toDouble() / 1E5))
            poly.add(latLng)
        }

        return poly
    }
}