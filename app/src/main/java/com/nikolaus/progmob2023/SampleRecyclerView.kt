package com.nikolaus.progmob2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikolaus.progmob2023.adapter.PetaniAdapter
import com.nikolaus.progmob2023.model.Petani

class SampleRecyclerView : AppCompatActivity() {
    lateinit var petaniAdapter: PetaniAdapter
    lateinit var lPetani: List<Petani>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_recycler_view)

        var rvSample: RecyclerView = findViewById(R.id.rv_latihan)

        lPetani = listOf(
            Petani(
                user = "AH",
                nama = "Argo Wibowo",
                jumlahLahan = "32 Hektar",
                identifikasi = "Positif",
                tambahLahan = "2 Hektar"
            ),
            Petani(
                user = "AH1",
                nama = "Argo",
                jumlahLahan = "20 Hektar",
                identifikasi = "Positif",
                tambahLahan = "5 Hektar"
            ),
            Petani(
                user = "AH2",
                nama = "Argo 2",
                jumlahLahan = "22 Hektar",
                identifikasi = "Positif",
                tambahLahan = "21 Hektar"
            ),
            Petani(
                user = "AH3",
                nama = "Argo Wibowo 3",
                jumlahLahan = "12 Hektar",
                identifikasi = "Negatif",
                tambahLahan = "0 Hektar"
            ),
            Petani(
                user = "AH4",
                nama = "1 Wibowo",
                jumlahLahan = "52 Hektar",
                identifikasi = "Positif",
                tambahLahan = "12 Hektar"
            )
        )
        petaniAdapter = PetaniAdapter(lPetani)

        rvSample.apply {
            layoutManager = LinearLayoutManager(this@SampleRecyclerView)
            adapter = petaniAdapter
        }
    }
}