package `in`.test.fruitfal_up.ui

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Generate formatted favourite event date
 *
 * @param inputDate is the  [input format ]
 * @return formattedDate is formatted date in dd-MMMM-yyyy
 */
fun formattedDate(inputDate: String): String {
    val inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    val outputPattern = "dd MMM yy"
    val inputFormat = SimpleDateFormat(inputPattern, Locale.ENGLISH)
    val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)
    var formattedDate: String = ""
    try {
        val date: Date = inputFormat.parse(inputDate)
        formattedDate = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return formattedDate
}