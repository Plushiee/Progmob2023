package com.nikolaus.progmob2023.crud

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.nikolaus.progmob2023.R
import com.nikolaus.progmob2023.model.ResponsePetaniItem
import com.nikolaus.progmob2023.network.NetworkConfigPetani
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertPetaniActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_petani)

        var baru = getIntent().getBooleanExtra("baru", false)
        val data = getIntent().getStringArrayExtra("data")

        var judulAPI: TextView = findViewById(R.id.tvJudulAPI)
        var edNamaAPI: EditText = findViewById(R.id.edNamaAPI)
        var edAlamatAPI: EditText = findViewById(R.id.edAlamatAPI)
        var edKelurahanAPI: EditText = findViewById(R.id.edKelurahanAPI)
        var edKecamatanAPI: EditText = findViewById(R.id.edKecamatanAPI)
        var edKabupatenAPI: EditText = findViewById(R.id.edKabupatenAPI)
        var edProvinsiAPI: EditText = findViewById(R.id.edProvinsiAPI)
        var edNamaIstriAPI: EditText = findViewById(R.id.edNamaIstriAPI)
        var edJumlahLahanAPI: EditText = findViewById(R.id.edJumlahLahanAPI)
        var edFotoAPI: EditText = findViewById(R.id.edFotoAPI)
        var btnSubmitAPI: Button = findViewById(R.id.btnSubmitAPI)

        //      Set Judul
        judulAPI.text = getIntent().getStringExtra("judul")

        if (!baru) {
            edNamaAPI.setText((data?.get(1) ?: edAlamatAPI.text))
            edAlamatAPI.setText((data?.get(2) ?: edAlamatAPI.text))
            edKelurahanAPI.setText((data?.get(3) ?: edAlamatAPI.text))
            edKecamatanAPI.setText((data?.get(4) ?: edAlamatAPI.text))
            edKabupatenAPI.setText((data?.get(5) ?: edAlamatAPI.text))
            edProvinsiAPI.setText((data?.get(6) ?: edAlamatAPI.text))
            edNamaIstriAPI.setText((data?.get(7) ?: edAlamatAPI.text))
            edJumlahLahanAPI.setText((data?.get(8) ?: edAlamatAPI.text))
            edFotoAPI.setText((data?.get(9) ?: edAlamatAPI.text))
        }

        //      cek data baru atau edit
        btnSubmitAPI.setOnClickListener(View.OnClickListener { View ->
            //      cek ada data kosong
            if (edNamaAPI.text.toString().isEmpty() ||
                edAlamatAPI.text.toString().isEmpty() ||
                edKelurahanAPI.text.toString().isEmpty() ||
                edKecamatanAPI.text.toString().isEmpty() ||
                edKabupatenAPI.text.toString().isEmpty() ||
                edProvinsiAPI.text.toString().isEmpty() ||
                edNamaIstriAPI.text.toString().isEmpty() ||
                edJumlahLahanAPI.text.toString().isEmpty() ||
                !edJumlahLahanAPI.text.toString().isDigitsOnly() ||
                edFotoAPI.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    applicationContext,
                    "Semua kolom harus diisi dan Jumlah Lahan harus berupa angka.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                if (baru) {
                    //      Insert Data API
                    NetworkConfigPetani().getService().postPetani(
                        edNamaAPI.text.toString(),
                        edAlamatAPI.text.toString(),
                        edKelurahanAPI.text.toString(),
                        edKecamatanAPI.text.toString(),
                        edKabupatenAPI.text.toString(),
                        edProvinsiAPI.text.toString(),
                        edNamaIstriAPI.text.toString(),
                        Integer.parseInt(edJumlahLahanAPI.text.toString()),
                        edFotoAPI.text.toString()
                    )?.enqueue(object : Callback<ResponsePetaniItem> {  // Fix the callback type
                        override fun onResponse(
                            call: Call<ResponsePetaniItem>,
                            response: Response<ResponsePetaniItem>
                        ) {
                            if (response.isSuccessful) {
                                // Keterangan respons yang berhasil
                                Toast.makeText(
                                    applicationContext,
                                    "Data petani baru berhasil ditambahkan",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(
                                    this@InsertPetaniActivity,
                                    ReadPetaniActivity::class.java
                                )
                                startActivity(intent)
                                runOnUiThread {
                                    finish()
                                }
                            } else {
                                // Keterangan respons yang gagal
                                val errorMessage =
                                    response.errorBody()?.string() // Get the error message
                                Toast.makeText(
                                    applicationContext,
                                    "Gagal menambahkan data petani baru. Error: $errorMessage",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponsePetaniItem>, t: Throwable) {
                            // Handle kegagalan jaringan atau permintaan gagal
                            Toast.makeText(
                                applicationContext,
                                "Error: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                } else {
                    //  Update
                    NetworkConfigPetani().getService().updatePetani(
                        data?.get(0),
                        edNamaAPI.text.toString(),
                        edAlamatAPI.text.toString(),
                        edKelurahanAPI.text.toString(),
                        edKecamatanAPI.text.toString(),
                        edKabupatenAPI.text.toString(),
                        edProvinsiAPI.text.toString(),
                        edNamaIstriAPI.text.toString(),
                        Integer.parseInt(edJumlahLahanAPI.text.toString()),
                        edFotoAPI.text.toString()
                    ).enqueue(object :
                        Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                // Keterangan respons yang berhasil
                                Toast.makeText(
                                    applicationContext,
                                    "Data petani baru berhasil diubah",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Reload
                                val intent = Intent(
                                    this@InsertPetaniActivity,
                                    ReadPetaniActivity::class.java
                                )
                                startActivity(intent)
                                runOnUiThread {
                                    finish()
                                }
                            } else {
                                // Handle unsuccessful deletion
                                val errorMessage =
                                    response.errorBody()?.string() // Get the error message
                                Toast.makeText(
                                    applicationContext,
                                    "Gagal mengubah data petani. Error: $errorMessage",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            // Handle failure
                            Toast.makeText(
                                applicationContext,
                                "Error: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        })
    }
}