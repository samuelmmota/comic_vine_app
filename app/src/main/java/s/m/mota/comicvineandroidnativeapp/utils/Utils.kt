package s.m.mota.comicvineandroidnativeapp.utils

import android.text.Spanned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {
    suspend fun parseHtmlAsync(html: String): Spanned {
        return withContext(Dispatchers.IO) {
            HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    fun parseHtmlToAnnotatedString(spannedHtml: Spanned): AnnotatedString {
        return buildAnnotatedString {
            append(spannedHtml.toString())

            // Handle bold styles
            spannedHtml.getSpans(0, spannedHtml.length, android.text.style.StyleSpan::class.java)
                .forEach { span ->
                    if (span.style == android.graphics.Typeface.BOLD) {
                        addStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold),
                            start = spannedHtml.getSpanStart(span),
                            end = spannedHtml.getSpanEnd(span)
                        )
                    } else if (span.style == android.graphics.Typeface.ITALIC) {
                        addStyle(
                            style = SpanStyle(fontStyle = FontStyle.Italic),
                            start = spannedHtml.getSpanStart(span),
                            end = spannedHtml.getSpanEnd(span)
                        )
                    }
                }

            // Handle underline styles
            spannedHtml.getSpans(
                0,
                spannedHtml.length,
                android.text.style.UnderlineSpan::class.java
            ).forEach { span ->
                addStyle(
                    style = SpanStyle(textDecoration = TextDecoration.Underline),
                    start = spannedHtml.getSpanStart(span),
                    end = spannedHtml.getSpanEnd(span)
                )
            }

            // Handle clickable spans
            spannedHtml.getSpans(
                0,
                spannedHtml.length,
                android.text.style.ClickableSpan::class.java
            ).forEach { span ->
                addStringAnnotation(
                    tag = "URL",
                    annotation = span.toString(),
                    start = spannedHtml.getSpanStart(span),
                    end = spannedHtml.getSpanEnd(span)
                )
                addStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = spannedHtml.getSpanStart(span),
                    end = spannedHtml.getSpanEnd(span)
                )
            }

            // Handle other custom spans (optional)
            spannedHtml.getSpans(
                0,
                spannedHtml.length,
                android.text.style.ForegroundColorSpan::class.java
            ).forEach { span ->
                addStyle(
                    style = SpanStyle(color = Color(span.foregroundColor)),
                    start = spannedHtml.getSpanStart(span),
                    end = spannedHtml.getSpanEnd(span)
                )
            }
        }
    }

    fun updatedDateMessage(dateString: String) : String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val updatedDate: Date = dateFormat.parse(dateString) ?: return null
        val currentDate = Calendar.getInstance()

        val updatedCalendar = Calendar.getInstance().apply { time = updatedDate }
        val yearDiff = currentDate.get(Calendar.YEAR) - updatedCalendar.get(Calendar.YEAR)
        val monthDiff = currentDate.get(Calendar.MONTH) - updatedCalendar.get(Calendar.MONTH)
        val dayDiff = currentDate.get(Calendar.DAY_OF_YEAR) - updatedCalendar.get(Calendar.DAY_OF_YEAR)

        return when {
            yearDiff == 0 && monthDiff == 0 && dayDiff == 0 -> "Updated today"
            yearDiff == 0 && monthDiff == 0 && dayDiff > 0 -> "Updated this month"
            yearDiff == 0 && monthDiff > 0 -> "Updated this year"
            yearDiff > 0 -> "Updated on ${SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(updatedDate)}"
            else -> "Invalid date"
        }
    }
}