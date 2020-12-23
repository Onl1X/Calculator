package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {

    private var variableFirst = 0.0
    private var variablesecond = 0.0
    private var operation = ""
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        init()

    }



    private fun operation() {
        var result: Double = 0.0
        when (operation) {
            ":" -> {
                result = (variableFirst / variablesecond).toDouble()

            }
            "+" -> {
                result = (variableFirst + variablesecond).toDouble()
            }
            "-" -> {
                result = (variableFirst - variablesecond).toDouble()
            }
            "X" -> {
                result = (variableFirst * variablesecond).toDouble()
            }
        }

        resultTextView.text = result.toString()

    }


    private fun init() {
        num0.setOnClickListener(this)
        num1.setOnClickListener(this)
        num2.setOnClickListener(this)
        num3.setOnClickListener(this)
        num4.setOnClickListener(this)
        num5.setOnClickListener(this)
        num6.setOnClickListener(this)
        num7.setOnClickListener(this)
        num8.setOnClickListener(this)
        num9.setOnClickListener(this)
        dot.setOnClickListener(this)
        delete.setOnLongClickListener(this)

        dot.setOnClickListener {
            val value = resultTextView.text.toString()
            if (value.isNotEmpty()) {
                resultTextView.text = resultTextView.text.toString() + "."
                dot.isClickable = false
            }
        }
    }

    fun delete(view: View) {
        val value = resultTextView.text.toString()
        if (value.isNotEmpty()) {
            resultTextView.text = value.substring(0, value.length - 1)
        }

    }

    fun divide(view: View) {
        val value = resultTextView.text.toString()
        if (value.isNotEmpty())
            variableFirst = value.toDouble()
        operation = ":"
        resultTextView.text = ""

    }

    fun multiply(view: View){
        val value = resultTextView.text.toString()
        if(value.isNotEmpty()){
            operation = "x"
            variableFirst = value.toDouble()
            resultTextView.text = ""
        }
    }

    fun minus(view: View) {
        val value = resultTextView.text.toString()
        if (value.isNotEmpty()) {
            variableFirst = resultTextView.text.toString().toDouble()
            operation = "-"
            resultTextView.text = ""
        }
    }

    fun equal(view: View) {
        val value = resultTextView.text.toString()
        if (value.isNotEmpty() && operation != "") {
            variablesecond = value.toDouble()
            var result = 0.0
            if(operation == ":" && variablesecond == 0.0) {
                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
            }else if (operation == ":" && variablesecond != 0.0) {
                result = variableFirst / variablesecond
            }else if (operation == "x"){
                result = variableFirst * variablesecond
            }else if (operation == "-"){
                result = variableFirst - variablesecond
            }else if (operation == "+"){
                result = variableFirst + variablesecond
            }

            if (result % 1.0 == 0.0){
                resultTextView.text = result.toInt().toString()
            }else{
                resultTextView.text = result.toString()
            }
        }
    }

    fun plus(view: View) {
        val value = resultTextView.text.toString()
        if (value.isNotEmpty()) {
            variableFirst = resultTextView.text.toString().toDouble()
            operation = "+"
            resultTextView.text = ""


        }

    }

    override fun onClick(v: View?) {
        val button = v as Button
        resultTextView.text = resultTextView.text.toString() + button.text.toString()
    }

    override fun onLongClick(v: View?): Boolean {
        resultTextView.text = ""
        return true
    }


}



