package br.com.acqio.extentions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss")

fun LocalDateTime.toFormatterString() : String {
    return this.format(formatter)
}