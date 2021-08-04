package com.example.kalkulatoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onDigit(view: View){
        tvInPut.append((view as Button).text)
        lastNumeric = true
    }
    fun onClear(view: View){
        tvInPut.text = ""
        lastNumeric = false
        lastDot = false

    }
    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvInPut.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded((tvInPut.text.toString()))){
            tvInPut.append((view as Button).text)
            lastDot = false
            lastNumeric = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    private fun removeAfterDot(result: String): String {
        var value = result
        if (value.contains(".0"))
            value = result.substring(0,result.length -2)
        return value
    }

    fun onEquel(view: View){
        if(lastNumeric){
            var tvValue = tvInPut.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInPut.text = removeAfterDot((one.toDouble() - two.toDouble()).toString())
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                }else  if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInPut.text = removeAfterDot((one.toDouble() + two.toDouble()).toString())
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                }else  if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInPut.text = removeAfterDot((one.toDouble() / two.toDouble()).toString())
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                }else  if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInPut.text = removeAfterDot((one.toDouble() * two.toDouble()).toString())
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}