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
import com.nikolaus.progmob2023.UTS.InsertUTSActivity
import com.nikolaus.progmob2023.UTS.ReadUTSActivity
import com.nikolaus.progmob2023.model.ResponseUTSItem
import com.nikolaus.progmob2023.network.NetworkConfigUTS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UTSAPIAdapter(
    val uts: List<ResponseUTSItem>?
) : RecyclerView.Adapter<UTSAPIAdapter.UTSHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UTSAPIAdapter.UTSHolder {
        return UTSHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_item_uts_api, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UTSAPIAdapter.UTSHolder, position: Int) {
        holder.bindUTS(uts?.get(position))
    }

    override fun getItemCount(): Int {
        return uts?.size ?: 0
    }

    class UTSHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var txtNamaUTSAPI: TextView
        lateinit var txtNimUTSAPI: TextView
        lateinit var txtAlamatUTSAPI: TextView
        lateinit var txtEmailUTSAPI: TextView
        lateinit var txtFotoUTSiAPI: TextView
        lateinit var btnDeleteUTSAPI: Button
        lateinit var btnEditUTSAPI: Button
        fun bindUTS(uts: ResponseUTSItem?) {
            itemView.apply {
                txtNamaUTSAPI = findViewById(R.id.namaUTSAPI)
                txtNimUTSAPI = findViewById(R.id.nimUTSAPI)
                txtAlamatUTSAPI = findViewById(R.id.alamatUTSAPI)
                txtEmailUTSAPI = findViewById(R.id.emailUTSAPI)
                txtFotoUTSiAPI = findViewById(R.id.fotoUTSAPI)
                btnDeleteUTSAPI = findViewById(R.id.btnDeleteUTSAPI)
                btnEditUTSAPI = findViewById(R.id.btnEditUTSAPI)

                txtNamaUTSAPI.text = uts?.nama
                txtNimUTSAPI.text = uts?.alamat
                txtAlamatUTSAPI.text = uts?.alamat
                txtEmailUTSAPI.text = uts?.email
                txtFotoUTSiAPI.text = uts?.foto

//              Delete
                btnDeleteUTSAPI.setOnClickListener(OnClickListener { View ->
                    NetworkConfigUTS().getService().deleteUTS(uts?.id, "72210456").enqueue(object :
                        Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                // Keterangan respons yang berhasil
                                Toast.makeText(
                                    context,
                                    "Data UTS baru berhasil dihapus",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Reload
                                val intent = Intent(context, ReadUTSActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            } else {
                                // Handle unsuccessful deletion
                                val errorMessage =
                                    response.errorBody()?.string() // Get the error message
                                Toast.makeText(
                                    context,
                                    "Gagal Menghapus data UTS baru. Error: $errorMessage",
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
                btnEditUTSAPI.setOnClickListener(OnClickListener { View ->
                    val intent = Intent(context, InsertUTSActivity::class.java)
                    val data = arrayOf(uts?.id,  uts?.nama, uts?.alamat, uts?.email, uts?.foto)

                    intent.putExtra("judul", "EDIT DATA UTS")
                    intent.putExtra("baru", false)
                    intent.putExtra("data", data)
                    context.startActivity(intent)
                    (context as Activity).finish()
                })
            }
        }
    }
}

