package com.nikolaus.progmob2023.UTS

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikolaus.progmob2023.LoginActivity
import com.nikolaus.progmob2023.R
import com.nikolaus.progmob2023.adapter.UTSAPIAdapter
import com.nikolaus.progmob2023.model.ResponseUTSItem
import com.nikolaus.progmob2023.network.NetworkConfigUTS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadUTSActivity : AppCompatActivity() {
    val prefs_name = "session_login"
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_uts)

        var rvUTSAPI: RecyclerView = findViewById(R.id.rvUTSAPI)
        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

        NetworkConfigUTS().getService().getUTS()
            .enqueue(object : Callback<List<ResponseUTSItem>> {
                override fun onFailure(
                    call: Call<List<ResponseUTSItem>>, t:
                    Throwable
                ) {
                    Toast.makeText(
                        this@ReadUTSActivity, t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<List<ResponseUTSItem>>,
                    response: Response<List<ResponseUTSItem>>
                ) {
                    val responseUTS = response.body()
                    rvUTSAPI.apply {
                        layoutManager = LinearLayoutManager(this@ReadUTSActivity)
                        adapter = UTSAPIAdapter(responseUTS)
                    }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnAddAPI -> {
                var intent = Intent(this@ReadUTSActivity, InsertUTSActivity::class.java)
                intent.putExtra("judul", "INSERT DATA UTS")
                intent.putExtra("baru", true)
                startActivity(intent)
//                finish()
            }
            R.id.btnLogoutAPI -> {
                val editor : SharedPreferences.Editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                finish()
                var intent = Intent(this@ReadUTSActivity, LoginActivity::class.java)
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