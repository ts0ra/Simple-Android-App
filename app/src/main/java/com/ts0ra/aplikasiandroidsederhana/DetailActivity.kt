package com.ts0ra.aplikasiandroidsederhana

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ts0ra.aplikasiandroidsederhana.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOB_DETAIL = "mob_detail"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var mobLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mob Detail"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataMob = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Mob>(MOB_DETAIL, Mob::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Mob>(MOB_DETAIL)
        }

        dataMob?.let { mob ->
            with(binding) {
                this.apply {
                    imgDetailPhoto.setImageResource(mob.photo)
                    tvHealthValue.text = mob.health
                    tvDamageValue.text = mob.damage
                    tvIdentifierValue.text = mob.identifier
                    tvItemDescriptionValue.text = mob.description
                    tvName.text = mob.name
                }
            }
            mobLink = mob.link
        }

        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener { view: View? ->
            when (view?.id) {
                R.id.action_share -> {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, mobLink)
                        type = "text/plain"
                    }
                    val shareLinkIntent = Intent.createChooser(intent, null)
                    startActivity(shareLinkIntent)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}