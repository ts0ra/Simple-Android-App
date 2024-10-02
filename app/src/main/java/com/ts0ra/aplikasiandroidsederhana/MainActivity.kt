package com.ts0ra.aplikasiandroidsederhana

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMobs: RecyclerView
    private val list = ArrayList<Mob>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvMobs = findViewById(R.id.rv_mobs)
        rvMobs.setHasFixedSize(true)

        list.addAll(getListMobs())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMobs() : ArrayList<Mob> {
        val dataName = resources.getStringArray(R.array.mob_names)
        val dataDescription = resources.getStringArray(R.array.mob_descriptions)
        val dataPhoto = resources.obtainTypedArray(R.array.mob_photos)
        val dataHealth = resources.getStringArray(R.array.mob_healths)
        val dataDamage = resources.getStringArray(R.array.mob_damages)
        val dataIdentifier = resources.getStringArray(R.array.mob_identifiers)
        val dataLink = resources.getStringArray(R.array.mob_links)
        val listMob = ArrayList<Mob>()
        for (i in dataName.indices) {
            val mob = Mob(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataHealth[i], dataDamage[i], dataIdentifier[i], dataLink[i])
            listMob.add(mob)
        }
        return listMob
    }

    private fun showRecyclerList() {
        rvMobs.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListMobAdapter(list)
        rvMobs.adapter = listHeroAdapter
    }
}