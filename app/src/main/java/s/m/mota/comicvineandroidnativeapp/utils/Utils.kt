package s.m.mota.comicvineandroidnativeapp.utils

import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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

            spannedHtml.getSpans(0, spannedHtml.length, android.text.style.StyleSpan::class.java).forEach { span ->
                //Add bold style
                if (span.style == android.graphics.Typeface.BOLD) {
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), spannedHtml.getSpanStart(span), spannedHtml.getSpanEnd(span))
                }
            }
        }
    }
}