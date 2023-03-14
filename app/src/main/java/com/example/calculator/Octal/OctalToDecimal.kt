package com.example.calculator.Octal

object OctalToDecimal {
    fun convertOctalToDecimal(octal: String): Int {
        return  Integer.parseInt(octal, 8)

    }
}
