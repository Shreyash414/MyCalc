package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber=0.0
    private var operation=""
    private var isNewOperation=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView=findViewById(R.id.resultTextView)
        previousCalculationTextView=findViewById(R.id.previousCalculationTextView)

        val Button0: Button=findViewById<Button>(R.id.btn0)
        val Button1: Button=findViewById<Button>(R.id.btn1)
        val Button2: Button=findViewById<Button>(R.id.btn2)
        val Button3: Button=findViewById<Button>(R.id.btn3)
        val Button4: Button=findViewById<Button>(R.id.btn4)
        val Button5: Button=findViewById<Button>(R.id.btn5)
        val Button6: Button=findViewById<Button>(R.id.btn6)
        val Button7: Button=findViewById<Button>(R.id.btn7)
        val Button8: Button=findViewById<Button>(R.id.btn8)
        val Button9: Button=findViewById<Button>(R.id.btn9)
        val Buttonadd: Button=findViewById<Button>(R.id.btnadd)
        val Buttonsubtract: Button=findViewById<Button>(R.id.btnsubtract)
        val Buttondivide: Button=findViewById<Button>(R.id.btndivide)
        val Buttondot: Button=findViewById<Button>(R.id.btndot)
        val Buttonclear: Button=findViewById<Button>(R.id.btnclear)
        val Buttondelete: Button=findViewById<Button>(R.id.btndelete)
        val Buttonequal: Button=findViewById<Button>(R.id.btnequal)
        val Buttonmultiply: Button=findViewById<Button>(R.id.btnmultiply)
        val Buttonpercent: Button=findViewById<Button>(R.id.btnpercent)


        //NUMBER CLICK ON LISTENER
        Button0.setOnClickListener{appendNumber("0")}
        Button1.setOnClickListener{appendNumber("1")}
        Button2.setOnClickListener{appendNumber("2")}
        Button3.setOnClickListener{appendNumber("3")}
        Button4.setOnClickListener{appendNumber("4")}
        Button5.setOnClickListener{appendNumber("5")}
        Button6.setOnClickListener{appendNumber("6")}
        Button7.setOnClickListener{appendNumber("7")}
        Button8.setOnClickListener{appendNumber("8")}
        Button9.setOnClickListener{appendNumber("9")}
        Buttondot.setOnClickListener{appendNumber(".")}

        //OPERATION CLICK LISTENER
        Buttonadd.setOnClickListener{setOperation("+")}
        Buttonsubtract.setOnClickListener{setOperation("-")}
        Buttondivide.setOnClickListener{setOperation("/")}
        Buttonmultiply.setOnClickListener{setOperation("*")}
        Buttonpercent.setOnClickListener{setOperation("%")}
        Buttonequal.setOnClickListener{calculateResult()}
        Buttonclear.setOnClickListener{clearCalculator()}
        Buttondelete.setOnClickListener { deleteNumber() }

    }
    private fun appendNumber(number: String){
           if(isNewOperation){
               resultTextView.text=number
               isNewOperation=false
       }
        else{
            resultTextView.text = "${resultTextView.text}$number"
        }}

    private fun setOperation(op: String){
            firstNumber=resultTextView.text.toString().toDouble()
            operation=op
            isNewOperation=true
            previousCalculationTextView.text="$firstNumber $operation"
        resultTextView.text="0.0"
        }

    private fun calculateResult(){
        try {
            val secondNumber: Double=resultTextView.text.toString().toDouble()
            val result: Double = when (operation){
            "+" -> firstNumber+secondNumber
            "-" -> firstNumber-secondNumber
            "/" -> firstNumber/secondNumber
            "*" -> firstNumber*secondNumber
            "%" -> firstNumber*(secondNumber/100)
            else -> secondNumber
        }
            previousCalculationTextView.text="$firstNumber $operation $secondNumber"
            resultTextView.text=result.toString()
            isNewOperation=true}
        catch (e: Exception){
            resultTextView.text="Error"
        }

    }


    private fun clearCalculator(){
        resultTextView.text="0"
        previousCalculationTextView.text=""
        firstNumber=0.0
        operation=""
        isNewOperation=true
    }

    private fun deleteNumber(){
        val currenttext=resultTextView.text.toString()
        if(currenttext.isNotEmpty() && currenttext!="0.0" && currenttext!="Error")
        { resultTextView.text=resultTextView.text.dropLast(1)}
        else{
            Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show()
        }
    }

    }
