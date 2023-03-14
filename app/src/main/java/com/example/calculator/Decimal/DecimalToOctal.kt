package com.example.calculator.Decimal

object DecimalToOctal {
    fun convertDecimalToOctal(decimal: String): String {
        return decimal.toUInt().toString(8)
    }
}
