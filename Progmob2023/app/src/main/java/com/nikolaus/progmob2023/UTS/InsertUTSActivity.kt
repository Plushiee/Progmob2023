package com.nikolaus.progmob2023.UTS

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.nikolaus.progmob2023.R
import com.nikolaus.progmob2023.model.ResponsePetaniItem
import com.nikolaus.progmob2023.model.ResponseUTSItem
import com.nikolaus.progmob2023.network.NetworkConfigPetani
import com.nikolaus.progmob2023.network.NetworkConfigUTS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertUTSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_uts)

        var baru = getIntent().getBooleanExtra("baru", false)
        val data = getIntent().getStringArrayExtra("data")

        var judulUTSAPI: TextView = findViewById(R.id.tvJudulUTSAPI)
        var edNamaUTSAPI: EditText = findViewById(R.id.edNamaUTSAPI)
        var edNimUTSAPI: EditText = findViewById(R.id.edNimUTSAPI)
        var edAlamatUTSAPI: EditText = findViewById(R.id.edAlamatUTSAPI)
        var edEmailUTSAPI: EditText = findViewById(R.id.edEmailUTSAPI)
        var edFotoUTSAPI: EditText = findViewById(R.id.edFotoUTSAPI)
        var btnSubmitAPI: Button = findViewById(R.id.btnSubmitAPI)

        //      Set Judul
        judulUTSAPI.text = getIntent().getStringExtra("judul")

        if (!baru) {
            Log.d("UTSAPIAdapter", "uts.id: ${data?.get(0)}, uts.nama: ${data?.get(1)}, uts.alamat: ${data?.get(2)}, uts.email: ${data?.get(3)}, uts.foto: ${data?.get(4)}")

            edNamaUTSAPI.setText((data?.get(1) ?: edNamaUTSAPI.text))
            edNimUTSAPI.setText((data?.get(2) ?: edNimUTSAPI.text))
            edAlamatUTSAPI.setText((data?.get(3) ?: edAlamatUTSAPI.text))
            edEmailUTSAPI.setText((data?.get(4) ?: edEmailUTSAPI.text))
            edFotoUTSAPI.setText((data?.get(5) ?: edFotoUTSAPI.text))
        }

        //      cek data baru atau edit
        btnSubmitAPI.setOnClickListener(View.OnClickListener { View ->
            //      cek ada data kosong
            if (edNamaUTSAPI.text.toString().isEmpty() ||
                edNimUTSAPI.text.toString().isEmpty() ||
                edAlamatUTSAPI.text.toString().isEmpty() ||
                edEmailUTSAPI.text.toString().isEmpty() ||
                edFotoUTSAPI.text.toString().isEmpty() ||
                !edNimUTSAPI.text.toString().isDigitsOnly()
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
                    NetworkConfigUTS().getService().postUTS(
                        edNamaUTSAPI.text.toString(),
                        edNimUTSAPI.text.toString(),
                        edAlamatUTSAPI.text.toString(),
                        edEmailUTSAPI.text.toString(),
                        edFotoUTSAPI.text.toString(),
                        "72210456"
                    )?.enqueue(object : Callback<Void> {  // Fix the callback type
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                // Keterangan respons yang berhasil
                                Toast.makeText(
                                    applicationContext,
                                    "Data UTS berhasil ditambahkan",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Reload
                                val intent = Intent(
                                    this@InsertUTSActivity,
                                    ReadUTSActivity::class.java
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
                                    "Gagal menambahkan data UTS. Error: $errorMessage",
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
                } else {
                    //  Update
                    NetworkConfigUTS().getService().updateUTS(
                        data?.get(0) ,
                        edNimUTSAPI.text.toString(),
                        edNamaUTSAPI.text.toString(),
                        edAlamatUTSAPI.text.toString(),
                        edEmailUTSAPI.text.toString(),
                        edFotoUTSAPI.text.toString(),
                        "72210456"
                    ).enqueue(object :
                        Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                // Keterangan respons yang berhasil
                                Toast.makeText(
                                    applicationContext,
                                    "Data petani UTS berhasil diubah",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Reload
                                val intent = Intent(
                                    this@InsertUTSActivity,
                                    ReadUTSActivity::class.java
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
                                    "Gagal mengubah data UTS. Error: $errorMessage",
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