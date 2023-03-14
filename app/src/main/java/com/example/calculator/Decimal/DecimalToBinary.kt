package com.example.calculator.Decimal

object DecimalToBinary {
    fun convertDecimalToBinary(n: String): String {
        return n.toUInt().toString(2)
    }
}

