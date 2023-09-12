package com.nikolaus.progmob2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ProteinTrackerActivity : AppCompatActivity() {
    lateinit var buttonEnter : Button
    lateinit var tv_total : TextView
    lateinit var tv_needed : TextView
    lateinit var ed_input_protein : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_protein_tracker)

        var needed = 175
        var totalForDays = 0

        tv_total = findViewById(R.id.tv_total)
        tv_needed = findViewById(R.id.tv_needed)
        ed_input_protein = findViewById(R.id.ed_input_protein)

        buttonEnter = findViewById(R.id.buttonEnter)
        buttonEnter.setOnClickListener(View.OnClickListener { View ->
            totalForDays += Integer.parseInt(ed_input_protein.text.toString())
            if (needed - Integer.parseInt(ed_input_protein.text.toString()) > 0) {
                needed -= Integer.parseInt(ed_input_protein.text.toString())
                tv_needed.text = needed.toString() + "g"
            } else {
                needed = 0
                tv_needed.text = "0g"
            }
            tv_total.text = totalForDays.toString() + "g"
        })
    }
}