package com.nikolaus.progmob2023.UTS

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.nikolaus.progmob2023.GetAPIActivity
import com.nikolaus.progmob2023.R

class LoginUTSActivity : AppCompatActivity() {
    val prefs_name = "session_login"
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_menu)

        var btnLoginPetani: Button = findViewById(R.id.btnLoginPetani)
        var edEmailPetani : EditText = findViewById(R.id.edEmailPetani)
        var edPasswordPetani : EditText = findViewById(R.id.edPasswordPetani)

        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

//      Baca session jika pernah login maka langsung ke getAPIActivity
        var tmpEmail = sharedPref.getString("email", null)
        var tmpPassword = sharedPref.getString("password", null)

        if(!tmpEmail.isNullOrEmpty() && !tmpPassword.isNullOrEmpty()) {
            finish()
            var intent = Intent(this@LoginUTSActivity, ReadUTSActivity::class.java)
            startActivity(intent)
        }

        btnLoginPetani.setOnClickListener(View.OnClickListener { view ->
            val sharedEditor : SharedPreferences.Editor = sharedPref.edit()
            sharedEditor.putString("email", edEmailPetani.text.toString())
            sharedEditor.putString("password", edPasswordPetani.text.toString())
            sharedEditor.apply()

//          pindah halaman ke GetAPIActivity
            finish()
            var intent = Intent(this@LoginUTSActivity, ReadUTSActivity::class.java)
            startActivity(intent)
        })
    }
}