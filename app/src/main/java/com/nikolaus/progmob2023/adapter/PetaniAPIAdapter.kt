package com.nikolaus.progmob2023.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nikolaus.progmob2023.R
import com.nikolaus.progmob2023.crud.InsertPetaniActivity
import com.nikolaus.progmob2023.crud.ReadPetaniActivity
import com.nikolaus.progmob2023.model.ResponsePetaniItem
import com.nikolaus.progmob2023.network.NetworkConfigPetani
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PetaniAPIAdapter(
    val petani: List<ResponsePetaniItem>?
) : RecyclerView.Adapter<PetaniAPIAdapter.PetaniHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetaniAPIAdapter.PetaniHolder {
        return PetaniHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_item_petani_api, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PetaniAPIAdapter.PetaniHolder, position: Int) {
        holder.bindPetani(petani?.get(position))
    }

    override fun getItemCount(): Int {
        return petani?.size ?: 0
    }

    class PetaniHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var txtNamaPetaniAPI: TextView
        lateinit var txtAlamatPetaniAPI: TextView
        lateinit var txtKelurahanPetaniAPI: TextView
        lateinit var txtKecamatapPetaniAPI: TextView
        lateinit var txtKabupatenPetaniAPI: TextView
        lateinit var txtProvinsiPetaniAPI: TextView
        lateinit var txtNamaIstriPetaniAPI: TextView
        lateinit var txtJumlahLahanPetaniAPI: TextView
        lateinit var txtFotoPetaniAPI: TextView
        lateinit var btnDeleteAPI: Button
        lateinit var btnEditAPI: Button
        fun bindPetani(petani: ResponsePetaniItem?) {
            itemView.apply {
                txtNamaPetaniAPI = findViewById(R.id.namaPetaniAPI)
                txtAlamatPetaniAPI = findViewById(R.id.alamatPetaniAPI)
                txtKelurahanPetaniAPI = findViewById(R.id.kelurahanPetaniAPI)
                txtKecamatapPetaniAPI = findViewById(R.id.kecamatanPetaniAPI)
                txtKabupatenPetaniAPI = findViewById(R.id.kabupatenPetaniAPI)
                txtProvinsiPetaniAPI = findViewById(R.id.provinsiPetaniAPI)
                txtNamaIstriPetaniAPI = findViewById(R.id.namaIstriPetaniAPI)
                txtJumlahLahanPetaniAPI = findViewById(R.id.jumlahLahanPetaniAPI)
                txtFotoPetaniAPI = findViewById(R.id.fotoPetaniAPI)
                btnDeleteAPI = findViewById(R.id.btnDeleteAPI)
                btnEditAPI = findViewById(R.id.btnEditAPI)

                txtNamaPetaniAPI.text = petani?.nama
                txtAlamatPetaniAPI.text = petani?.alamat
                txtKelurahanPetaniAPI.text = petani?.kelurahan
                txtKecamatapPetaniAPI.text = petani?.kecamatan
                txtKabupatenPetaniAPI.text = petani?.kabupaten
                txtProvinsiPetaniAPI.text = petani?.provinsi
                txtNamaIstriPetaniAPI.text = petani?.namaIstri
                txtJumlahLahanPetaniAPI.text = petani?.jumlahLahan
                txtFotoPetaniAPI.text = petani?.foto

//              Delete
                btnDeleteAPI.setOnClickListener(OnClickListener { View ->
                    NetworkConfigPetani().getService().deletePetani(petani?.id).enqueue(object :
                        Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                // Keterangan respons yang berhasil
                                Toast.makeText(
                                    context,
                                    "Data petani baru berhasil dihapus",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Reload
                                val intent = Intent(context, ReadPetaniActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            } else {
                                // Handle unsuccessful deletion
                                val errorMessage =
                                    response.errorBody()?.string() // Get the error message
                                Toast.makeText(
                                    context,
                                    "Gagal Menghapus data petani baru. Error: $errorMessage",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            // Handle failure
                            Toast.makeText(
                                context,
                                "Error: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                })

//          Edit
                btnEditAPI.setOnClickListener(OnClickListener { View ->
                    val intent = Intent(context, InsertPetaniActivity::class.java)
                    val data = arrayOf(petani?.id,  petani?.nama, petani?.alamat, petani?.kelurahan, petani?.kecamatan, petani?.kabupaten, petani?.provinsi, petani?.namaIstri, petani?.jumlahLahan, petani?.foto)

                    intent.putExtra("judul", "EDIT DATA PETANI")
                    intent.putExtra("baru", false)
                    intent.putExtra("data", data)
                    context.startActivity(intent)
                    (context as Activity).finish()
                })
            }
        }
    }
}

