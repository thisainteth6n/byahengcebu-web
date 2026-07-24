package com.byahengcebu.mobile.shared.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    private val input =
        DateTimeFormatter.ISO_LOCAL_DATE_TIME

    private val dateFormatter =
        DateTimeFormatter.ofPattern("MMM dd, yyyy")

    private val timeFormatter =
        DateTimeFormatter.ofPattern("hh:mm a")

    fun formatDate(date: String?): String {

        if (date.isNullOrBlank())
            return "-"

        return try {

            LocalDateTime
                .parse(date, input)
                .format(dateFormatter)

        } catch (e: Exception) {

            date

        }

    }

    fun formatTime(date: String?): String {

        if (date.isNullOrBlank())
            return "-"

        return try {

            LocalDateTime
                .parse(date, input)
                .format(timeFormatter)

        } catch (e: Exception) {

            "-"

        }

    }

}