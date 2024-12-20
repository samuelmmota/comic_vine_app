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
}