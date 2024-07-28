package com.example.bmicalculator1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edWeight = findViewById<EditText>(R.id.edWeight)
        val edHeight = findViewById<EditText>(R.id.edHeight)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val weight = edWeight.text.toString()
            val height = edHeight.text.toString()

            if (validateInput(weight, height)){
                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                // to get Result with two decimal places
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayRes(bmi2digits)
            }
        }

    }

    private fun validateInput(weight : String?, height: String?): Boolean {
        return when{
            weight.isNullOrBlank() -> {
                Toast.makeText(this, "Please Enter Weight", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrBlank() -> {
                Toast.makeText(this, "Please Enter Height", Toast.LENGTH_LONG).show()
                return false
            }
            else -> true
        }
    }


    private fun displayRes(bmi : Float){
        val tvResultsIndex = findViewById<TextView>(R.id.tvResultsIndex)
        val tvResultStatment = findViewById<TextView>(R.id.tvResultStatment)
        val tvResultInfo = findViewById<TextView>(R.id.tvResultInfo)

        tvResultsIndex.text = bmi.toString()
        tvResultInfo.text = "(Normal range is 18.5 to 24.9)"

        var statmentRes = ""
        var color = 0

        when{
            bmi<18.5 ->{
                statmentRes = "Underweight"
                color = R.color.Underweight
            }
            bmi in 18.51..24.99->{
                statmentRes = "Healthy"
                color = R.color.Normal
            }
            bmi in 25.00..29.99->{
                statmentRes = "Overweight"
                color = R.color.obesse
            }
            bmi > 29.99 ->{
                statmentRes = "Obese"
                color = R.color.overweight
            }
        }

        tvResultStatment.setTextColor(ContextCompat.getColor(this, color))
        tvResultStatment.text = statmentRes
    }

}