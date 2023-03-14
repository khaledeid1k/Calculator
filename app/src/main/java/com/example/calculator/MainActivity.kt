package com.example.calculator

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.method.DigitsKeyListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.calculator.Binary.BinaryToDecimal.convertBinaryToDecimal
import com.example.calculator.Decimal.DecimalToBinary.convertDecimalToBinary
import com.example.calculator.Decimal.DecimalToHexa.convertDecimalToHexa
import com.example.calculator.Decimal.DecimalToOctal.convertDecimalToOctal
import com.example.calculator.Hexa.HexaToDecimal.convertHexaToDecimal
import com.example.calculator.Octal.OctalToDecimal.convertOctalToDecimal
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }


    override fun onResume() {
        super.onResume()

        addStringsToList()

        printErrorIfTextEmpty()

        covertFromTo()

        changeKeyboardInput()

        changeColorOfDropList()


    }

    private fun changeKeyboardInput() {
        binding.menuConvertFromText.doOnTextChanged { text, _, _, _ ->
            // make enter text empty if convert from change .
            binding.enterNumberText.setText("")
            // chang keyboard according to convert from Text .
            when (text.toString()) {
                "Binary" -> {
                    binding.enterNumberText.keyListener = DigitsKeyListener.getInstance("01")
                }
                "Decimal" -> {
                    binding.enterNumberText.keyListener =
                        DigitsKeyListener.getInstance("0123456789")
                }
                "HexDecimal" -> {
                    binding.enterNumberText.inputType = TYPE_CLASS_TEXT
                }
                "Octal" -> {
                    binding.enterNumberText.keyListener = DigitsKeyListener.getInstance("01234567")
                }
            }
        }
    }

    private fun covertFromTo() {

        binding.convert.setOnClickListener {
            val enterNumber = binding.enterNumberText.text.toString().trim()
            if (enterNumber.isEmpty()) {
                binding.enterNumber.error = "Field can't be empty"
            } else {
                converter(enterNumber)
            }
        }

    }

    private fun converter(enterNumber: String) {

        val numberFrom = binding.menuConvertFromText.text.toString()
        if (numberFrom.isEmpty()) {
            Toast.makeText(this, "Please, Chose what you need convert from", Toast.LENGTH_SHORT)
                .show()
        }

        val numberTo = binding.menuConvertToText.text.toString()
        if (numberTo.isEmpty()) {
            Toast.makeText(this, "Please, Chose what you need convert to", Toast.LENGTH_SHORT)
                .show()
        }


        when (numberFrom) {
            "Binary" -> checkNumberFrom(
                numberTo,
                convertBinaryToDecimal(enterNumber.toLong()).toString()
            )
            "Decimal" -> checkNumberFrom(numberTo, enterNumber)
            "Octal" -> checkNumberFrom(numberTo, convertOctalToDecimal(enterNumber).toString())
            "HexDecimal" -> {
                try {
                    checkNumberFrom(numberTo, convertHexaToDecimal(enterNumber).toString())
                } catch (nfe: NumberFormatException) {
                    Toast.makeText(this, "not a valid hex", Toast.LENGTH_SHORT).show()
                }

            }
        }


    }

    private fun checkNumberFrom(numberTo: String, enterNumber: String) {
        when (numberTo) {
            "Binary" -> binding.resultText.text = convertDecimalToBinary(enterNumber)
            "Decimal" -> binding.resultText.text = enterNumber
            "Octal" -> binding.resultText.text = convertDecimalToOctal(enterNumber)
            "HexDecimal" -> binding.resultText.text = convertDecimalToHexa(enterNumber.toInt())
        }
    }


    private fun printErrorIfTextEmpty() {
        binding.enterNumberText.doOnTextChanged { text, start, before, count ->
            binding.enterNumber.error = null
        }
    }

    private fun addStringsToList() {
        val types = resources.getStringArray(R.array.number)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, types)
        binding.menuConvertFromText.setAdapter(arrayAdapter)
        binding.menuConvertToText.setAdapter(arrayAdapter)
    }


    private fun changeColorOfDropList() {
        binding.menuConvertFromText.setDropDownBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.lowGreen))
        )
        binding.menuConvertToText.setDropDownBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.lowGreen))
        )
    }

}
