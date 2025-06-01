package com.joohnq.domain.mapper

import com.joohnq.domain.entity.FormatStyle
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterFullStyle
import platform.Foundation.NSDateFormatterLongStyle
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSDateFormatterNoStyle
import platform.Foundation.NSDateFormatterShortStyle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun LocalDate.formatDateToLocale(style: FormatStyle): String {
    val style = when (style) {
        FormatStyle.FULL -> NSDateFormatterFullStyle
        FormatStyle.LONG -> NSDateFormatterLongStyle
        FormatStyle.MEDIUM -> NSDateFormatterMediumStyle
        FormatStyle.SHORT -> NSDateFormatterShortStyle
    }
    val formatter = NSDateFormatter().apply {
        dateStyle = style
        timeStyle = NSDateFormatterNoStyle
        locale = NSLocale.currentLocale
    }

    val calendar = NSCalendar.currentCalendar
    val nsDate = calendar.dateFromComponents(this.toNSDateComponents()) ?: return ""
    return formatter.stringFromDate(nsDate)
}

actual fun LocalDate.formatDateToLocale(pattern: String): String {
    val formatter = NSDateFormatter().apply {
        dateFormat = pattern
        locale = NSLocale.currentLocale
    }

    val calendar = NSCalendar.currentCalendar
    val nsDate = calendar.dateFromComponents(this.toNSDateComponents()) ?: return ""
    return formatter.stringFromDate(nsDate)
}

actual fun LocalDateTime.formatDateToLocale(style: FormatStyle): String =
    date.formatDateToLocale(style)