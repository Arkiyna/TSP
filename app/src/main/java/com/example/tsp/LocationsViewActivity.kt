package com.example.tsp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LocationsViewActivity : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private lateinit var mAdapter: MultiselectAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations_view)

        val addresses = arrayOf("Poljanski nasip 30, Ljubljana", "Pražakova ulica 3, Ljubljana", "Zaloška cesta 57, Ljubljana",
            "Dunajska cesta 141, Ljubljana", "Riharjeva ulica 38, Ljubljana", "Vodnikova cesta 235, Ljubljana",
            "Resljeva cesta 14, Ljubljana", "Prušnikova ulica 2, Ljubljana", "Glavarjeva cesta 39, Komenda",
            "Dunajska cesta 361, Ljubljana", "Trdinov trg 8 A, Mengeš", "Ljubljanska cesta 14 A, Kamnik",
            "Vegova ulica 1, Moravče", "Zadobrovška cesta 14, Ljubljana Polje", "Partizanska cesta 7, Grosuplje",
            "Sokolska ulica 4, 1295", "Kolodvorska ulica 2, Ribnica", "Ljubljanska cesta 23, Kočevje",
            "Podpeška cesta 20, Brezovica pri Ljubljani", "Molkov trg 12 , Borovnica", "Poštna ulica 2, Vrhnika",
            "Cesta zmage 28, Zagorje ob Savi", "Trg revolucije 27, Trbovlje", "Trg Franca Fakina 4, Trbovlje",
            "Istrska ulica 49, Maribor", "Šarhova ulica 59 A, Maribor", "Sladki Vrh 3 A, Sladki Vrh", "Malečnik 56, Malečnik",
            "Partizanska cesta 3, 2230", "Čolnikov trg 9, Benedikt", "Cesta k Dravi 5, Spodnji Duplek",
            "Mariborska Cesta 19, Ptuj", "Ivanjkovci 9 B, Ivanjkovci", "Poštna ulica 2, Ormož", "Gorišnica 79, Gorišnica",
            "Videm pri Ptuju 51 A, Videm pri Ptuju", "Mariborska ulica 26, Zgornja Polskava", "Makole 37, Makole", "Kopališka ulica 2, Kidričevo",
            "Ljubljanska cesta 14, Rače", "Cesta v Rošpoh 18, Kamnica", "Slovenski trg 4, Selnica ob Dravi", "Obrobna ulica 1, Brestrnica",
            "Glavni trg 31, Muta", "Trg 4. julija 1, Dravograd", "Trg svobode 19, Ravne na Koroškem", "Trg 32 A, Prevalje",
            "Center 16, Črna na Koroškem", "Krekov trg 9, Celje", "Aškerčev trg 26, Šmarje pri Jelšah", "Ulica heroja Staneta 1, Žalec",
            "Malteška cesta 38, Polzela", "Savinjska cesta 3, Mozirje", "Dražgoška ulica 8, Kranj", "Škofjeloška cesta 17, Kranj",
            "Jezerska cesta 41, Kranj", "Ulica Lojzeta Hrovata 2, Kranj", "Glavna cesta 24, Naklo", "Gasilska cesta 2 A, Šenčur",
            "Kapucinski trg 14, Škofja Loka", "Trg svobode 1, Žiri", "Na Kresu 1, Železniki", "Alpska cesta 37 B, Lesce",
            "Trg svobode 2 C, Bohinjska Bistrica", "Cesta Cirila Tavčarja 8, Jesenice", "Žirovnica 4 , Žirovnica",
            "Borovška cesta 92, Kranjska Gora", "Predilniška cesta 10, Tržič", "Kidričeva ulica 19, Nova Gorica",
            "Industrijska cesta 9, Nova Gorica", "Trg maršala Tita 10, Tolmin", "Goriška cesta 24, Ajdovščina",
            "Vodnikova ulica 1, Idrija", "Vrtojbenska cesta 19 C, Šempeter pri Gorici", "Kolodvorska cesta 9, Koper",
            "Partizanska cesta 48 A, Sežana", "Ulica 1. maja 2 A, Postojna", "Cankarjev drevored 1, Izola", "Ulica Slavka Gruma 7, Novo Mesto",
            "Novi trg 7, Novo mesto", "Goliev trg 11, Trebnje", "Cesta na Fužine 3, Mirna", "Ulica stare pravde 34, Brežice",
            "Ulica bratov Gerjovičev 52, Dobova", "Jesenice 9, Jesenice na Dolenjskem", "Trg Matije Gubca 1, Krško", "Trg svobode 9, Sevnica",
            "Prvomajska cesta 3, Šentjernej", "Naselje Borisa Kidriča 2, Metlika", "Kolodvorska cesta 30 A, Črnomelj",
            "Trg Zmage 6, Murska Sobota", "Gornji Petrovci 40 E, Petrovci", "Trg ljudske pravice 7, Lendava",
            "Ulica Prekmurske čete 14, Črenšovci", "Cesta na Stadion 1, Gornja Radgona", "Panonska cesta 5, Radenci")



        val dataset = ArrayList<DataSet>()

        for(i in 0..addresses.size - 1) {
            dataset.add(DataSet(i, addresses[i], false))
        }



        mAdapter = MultiselectAdapter(dataset) { show -> showDeleteMenu(show) }
        val rv: RecyclerView = findViewById(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter

    }

    fun showDeleteMenu(show: Boolean) {
        mainMenu?.findItem(R.id.mSelect)?.isVisible = show
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mainMenu = menu
        menuInflater.inflate(R.menu.custom_menu, mainMenu)
        showDeleteMenu(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mSelect -> { delete() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun delete() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Zagon algoritma")
        alertDialog.setMessage("Ali želite zagnati algoritem nad izbranimi mesti?")
        alertDialog.setPositiveButton("Da") {_, _ ->
            val test : ArrayList<Int> = mAdapter.deleteSelectedItem()
            showDeleteMenu(false)
            val intent = Intent(this, MapsActivity::class.java);
            intent.putExtra("cities", test)

            startActivity(intent)
        }
        alertDialog.setNegativeButton("Ne") {_, _ -> }
        alertDialog.show()
    }
}