package com.example.calculator.Decimal

object DecimalToHexa {
    fun convertDecimalToHexa(num : Int) : String{
        return num.toUInt().toString(16)
//        return (Integer.toHexString(num))
    }



}