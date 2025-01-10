package s.m.mota.comicvineandroidnativeapp.data.model.html

import androidx.compose.ui.text.AnnotatedString

sealed class ComicHtmlAnnotatedElement {
    data class Text(val content: AnnotatedString) : ComicHtmlAnnotatedElement()
    data class Url(val urlText: String, val urlLink: String?) : ComicHtmlAnnotatedElement()
    data class Title(val title: String) : ComicHtmlAnnotatedElement()
    data class Header(val header: String) : ComicHtmlAnnotatedElement()
    data class Image(val imageUrl: String) : ComicHtmlAnnotatedElement()
}