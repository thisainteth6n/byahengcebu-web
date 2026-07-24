package com.byahengcebu.mobile.shared.utils

import java.text.NumberFormat
import java.util.Locale

object CurrencyUtils {

    private val peso =
        NumberFormat.getCurrencyInstance(Locale("en", "PH"))

    fun format(amount: Double): String {

        return peso.format(amount)

    }

}