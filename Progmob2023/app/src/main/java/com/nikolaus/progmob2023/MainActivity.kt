package com.nikolaus.progmob2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tvMain : TextView
    lateinit var button : Button
    lateinit var buttonHelp : Button
    lateinit var buttonLinear : Button
    lateinit var edInputNama : EditText
    lateinit var buttonConstraint : Button
    lateinit var buttonTable : Button
    lateinit var buttonProtein : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMain = findViewById(R.id.textView2)
        tvMain.text = "Hello Progmob 2023"

        button = findViewById(R.id.button)
        button.text = getString(R.string.progmob_2023)
        button.setOnClickListener(View.OnClickListener { View ->
            tvMain.text = "Hello " + edInputNama.text.toString()
        })

        buttonHelp = findViewById(R.id.buttonHelp)
        buttonHelp.text = "Help"

        edInputNama = findViewById(R.id.ed_input_nama)

        buttonProtein = findViewById(R.id.buttonProtein)
        buttonProtein.setOnClickListener (View.OnClickListener { View ->
            var intent = Intent(this@MainActivity, ProteinTrackerActivity::class.java)
            startActivity(intent)
        })

        buttonTable = findViewById(R.id.buttonTable)
        buttonTable.setOnClickListener(View.OnClickListener { View ->
            var intent = Intent(this@MainActivity, TableActivity::class.java)
            startActivity(intent)
        })

        buttonConstraint = findViewById(R.id.buttonConstraint)
        buttonConstraint.setOnClickListener(View.OnClickListener { View ->
            var intent = Intent(this@MainActivity, ConstraintActivity::class.java)
            startActivity(intent)
        })

        buttonLinear = findViewById(R.id.buttonLinear)
        buttonLinear.setOnClickListener(View.OnClickListener { View ->
            var intent = Intent(this@MainActivity, LayoutActivity::class.java)
            startActivity(intent)
        })

        buttonHelp.setOnClickListener(View.OnClickListener { View ->
            var bundle = Bundle()
            var strTmp = edInputNama.text.toString()
            bundle.putString("tesText", strTmp)

            var intent = Intent(this@MainActivity, HelpActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })
    }
}