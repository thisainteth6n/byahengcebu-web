package com.byahengcebu.mobile.shared.utils

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    private val parser =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")

    private val dateFormatter =
        DateTimeFormatter.ofPattern("MMM dd, yyyy")

    private val timeFormatter =
        DateTimeFormatter.ofPattern("hh:mm a")

    fun formatDate(date: String?): String {

        if (date.isNullOrBlank()) return "--"

        return try {

            LocalDateTime.parse(date, parser)
                .format(dateFormatter)

        } catch (e: Exception) {

            "--"

        }

    }

    fun formatTime(date: String?): String {

        if (date.isNullOrBlank()) return "--"

        return try {

            LocalDateTime.parse(date, parser)
                .format(timeFormatter)

        } catch (e: Exception) {

            "--"

        }

    }

    fun formatDateTime(date: String?): String {

        if (date.isNullOrBlank()) return "--"

        return try {

            val dt = LocalDateTime.parse(date, parser)

            "${dt.format(dateFormatter)} • ${dt.format(timeFormatter)}"

        } catch (e: Exception) {

            "--"

        }

    }

    fun duration(start: String?, end: String?): String {

        if (start.isNullOrBlank() || end.isNullOrBlank()) return "--"

        return try {

            val s = LocalDateTime.parse(start, parser)
            val e = LocalDateTime.parse(end, parser)

            val d = Duration.between(s, e)

            val hours = d.toHours()
            val mins = d.toMinutes() % 60

            "${hours}h ${mins}m"

        } catch (e: Exception) {

            "--"

        }

    }

}