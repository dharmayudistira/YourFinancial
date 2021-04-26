package com.pandecode.yourfinancial.utils

import java.text.NumberFormat
import java.util.*

object RupiahFormatter {
    fun toRupiah(value: Double) : String {
        val localeID = Locale("in", "ID")

        val rupiahFormat = NumberFormat.getCurrencyInstance(localeID)
        return rupiahFormat.format(value)
    }
}