package com.nikolaus.progmob2023.crud

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikolaus.progmob2023.LoginActivity
import com.nikolaus.progmob2023.R
import com.nikolaus.progmob2023.adapter.PetaniAPIAdapter
import com.nikolaus.progmob2023.model.ResponsePetani
import com.nikolaus.progmob2023.model.ResponsePetaniItem
import com.nikolaus.progmob2023.network.NetworkConfigPetani
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadPetaniActivity : AppCompatActivity() {
    val prefs_name = "session_login"
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_petani)

        var rvPetaniAPI: RecyclerView = findViewById(R.id.rvPetaniAPI)
        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

        NetworkConfigPetani().getService().getPetani()
            ?.enqueue(object : Callback<ResponsePetani> {
                override fun onFailure(
                    call: Call<ResponsePetani>, t:
                    Throwable
                ) {
                    Toast.makeText(
                        this@ReadPetaniActivity, t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<ResponsePetani>,
                    response: Response<ResponsePetani>
                ) {
                    val responseData = response.body()?.data
                    rvPetaniAPI.apply {
                        layoutManager = LinearLayoutManager(this@ReadPetaniActivity)
                        adapter = PetaniAPIAdapter(responseData)
                    }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnAddAPI -> {
                var intent = Intent(this@ReadPetaniActivity, InsertPetaniActivity::class.java)
                intent.putExtra("judul", "INSERT DATA PETANI")
                intent.putExtra("baru", true)
                startActivity(intent)
                finish()
            }
            R.id.btnLogoutAPI -> {
                val editor : SharedPreferences.Editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                finish()
                var intent = Intent(this@ReadPetaniActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_add_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }
}