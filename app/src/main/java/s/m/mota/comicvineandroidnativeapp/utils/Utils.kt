package s.m.mota.comicvineandroidnativeapp.utils

import android.text.Spanned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.core.text.HtmlCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchOrderSetting
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchSortSetting
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
data class ComicHtmlElement(
    val text: AnnotatedString? = null,
    val imageUrl: String? = null
)
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
                0, spannedHtml.length, android.text.style.UnderlineSpan::class.java
            ).forEach { span ->
                addStyle(
                    style = SpanStyle(textDecoration = TextDecoration.Underline),
                    start = spannedHtml.getSpanStart(span),
                    end = spannedHtml.getSpanEnd(span)
                )
            }

            // Handle clickable spans
            spannedHtml.getSpans(0, spannedHtml.length, android.text.style.URLSpan::class.java)
                .forEach { span ->
                    val start = spannedHtml.getSpanStart(span)
                    val end = spannedHtml.getSpanEnd(span)

                    /*val link =
                        LinkAnnotation.Url(
                            span.url,
                            TextLinkStyles(SpanStyle(color = Color.Blue))
                        ) {
                            val url = (it as LinkAnnotation.Url).url
                            // log some metrics
                           // uriHandler.openUri(url)
                            println("Clicked URL: $url")
                        }
                    addLink(
                        start = start,
                        end = end,
                        url = link
                    )*/
                    addStringAnnotation(
                        tag = "URL", annotation = span.url, start = start, end = end
                    )
                    addStyle(
                        style = SpanStyle(
                            color = Color.Blue, textDecoration = TextDecoration.Underline
                        ), start = start, end = end
                    )
                }

            // Handle other custom spans
            spannedHtml.getSpans(
                0, spannedHtml.length, android.text.style.ForegroundColorSpan::class.java
            ).forEach { span ->
                addStyle(
                    style = SpanStyle(color = Color(span.foregroundColor)),
                    start = spannedHtml.getSpanStart(span),
                    end = spannedHtml.getSpanEnd(span)
                )
            }
        }
    }

    fun formatedDateMessage(dateString: String): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val updatedDate: Date = dateFormat.parse(dateString) ?: return null
        val currentDate = Calendar.getInstance()

        val updatedCalendar = Calendar.getInstance().apply { time = updatedDate }
        val yearDiff = currentDate.get(Calendar.YEAR) - updatedCalendar.get(Calendar.YEAR)
        val monthDiff = currentDate.get(Calendar.MONTH) - updatedCalendar.get(Calendar.MONTH)
        val weekDiff =
            currentDate.get(Calendar.WEEK_OF_YEAR) - updatedCalendar.get(Calendar.WEEK_OF_YEAR)
        val dayDiff =
            currentDate.get(Calendar.DAY_OF_YEAR) - updatedCalendar.get(Calendar.DAY_OF_YEAR)

        return when {
            yearDiff == 0 && monthDiff == 0 && dayDiff == 0 -> "today"
            yearDiff == 0 && weekDiff == 0 -> "this week"
            yearDiff == 0 && monthDiff == 0 && dayDiff > 0 -> "this month"
            yearDiff == 0 && monthDiff > 0 -> "this year"
            yearDiff > 0 -> "on ${
                SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                    updatedDate
                )
            }"

            else -> null
        }
    }

    fun Pair<FetchSortSetting, FetchOrderSetting>.toSortStringFormat(): String {
        return "${first.jsonName}:${this.second.jsonName}"
    }

    fun resolvePath(inputPath: String): String {
        val parts = inputPath.split("/")
        val resolvedParts = mutableListOf<String>()

        for (part in parts) {
            when {
                part.isEmpty() || part == "." || part == ".." || part == "https:" || part == "www.comicvine.com" || part == "comicvine.gamespot.com" -> {
                    continue
                }

                else -> {
                    resolvedParts.add(part)
                }
            }
        }

        return resolvedParts.joinToString(separator = "/", postfix = "/")
    }


    fun parseHtmlToHtmlElements(spannedHtml: Spanned): List<ComicHtmlElement> {
        val elements = mutableListOf<ComicHtmlElement>()

        var currentTextStart = 0

        // Iterate over all spans in the HTML
        spannedHtml.getSpans(0, spannedHtml.length, Any::class.java).forEach { span ->
            val spanStart = spannedHtml.getSpanStart(span)
            val spanEnd = spannedHtml.getSpanEnd(span)

            // Handle text before the current span
            if (currentTextStart < spanStart) {
                val text = spannedHtml.subSequence(currentTextStart, spanStart).toString()
                elements.add(ComicHtmlElement(text = AnnotatedString(text)))
            }

            when (span) {
                is android.text.style.ImageSpan -> {
                    // Add image element
                    elements.add(ComicHtmlElement(imageUrl = span.source))
                }
                is android.text.style.StyleSpan -> {
                    // Handle bold and italic styles
                    if (span.style == android.graphics.Typeface.BOLD || span.style == android.graphics.Typeface.ITALIC) {
                        val text = spannedHtml.subSequence(spanStart, spanEnd).toString()
                        val style = if (span.style == android.graphics.Typeface.BOLD) {
                            SpanStyle(fontWeight = FontWeight.Bold)
                        } else {
                            SpanStyle(fontStyle = FontStyle.Italic)
                        }
                        elements.add(ComicHtmlElement(text = buildAnnotatedString {
                            withStyle(style) {
                                append(text)
                            }
                        }))
                    }
                }
                is android.text.style.URLSpan -> {
                    // Handle clickable links
                    val url = span.url
                    val text = spannedHtml.subSequence(spanStart, spanEnd).toString()
                    elements.add(ComicHtmlElement(text = buildAnnotatedString {
                        append(text)
                        addStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            ),
                            start = 0,
                            end = text.length
                        )
                        addStringAnnotation(tag = "URL", annotation = url, start = 0, end = text.length)
                    }))
                }
            }

            currentTextStart = spanEnd
        }

        // Handle remaining text
        if (currentTextStart < spannedHtml.length) {
            val text = spannedHtml.subSequence(currentTextStart, spannedHtml.length).toString()
            elements.add(ComicHtmlElement(text = AnnotatedString(text)))
        }

        return elements
    }
}