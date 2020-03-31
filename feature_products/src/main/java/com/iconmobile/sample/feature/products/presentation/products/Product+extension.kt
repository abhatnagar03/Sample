package com.iconmobile.sample.feature.products.presentation.products

import android.content.Context
import com.iconmobile.sample.feature.products.R
import java.text.NumberFormat
import java.util.*

fun Context.transformPrice(currency: String?, price: Double?): String =
    String.format(
        resources.getString(
            R.string.product_price
        ),
        currency?.formatCurrency(Locale.getDefault()), price?.formatPrice(Locale.getDefault())
    )

fun String.formatCurrency(locale: Locale): String =
    Currency.getInstance(this).getSymbol(locale)

fun Double.formatPrice(locale: Locale): String {
    val format: NumberFormat = NumberFormat.getNumberInstance(locale)
    format.minimumFractionDigits = 2
    return format.format(this)
}