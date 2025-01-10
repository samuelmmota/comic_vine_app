package s.m.mota.comicvineandroidnativeapp.ui.component.text

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.coil.CoilImage
import s.m.mota.comicvineandroidnativeapp.data.model.html.ComicHtmlAnnotatedElement
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.utils.Utils
import s.m.mota.comicvineandroidnativeapp.utils.WEBVIEW_COMIC_VINE_URL
import timber.log.Timber

@Composable
fun DisplayComicHtmlAnnotatedElement(
    annotatedElement: ComicHtmlAnnotatedElement,
    navController: NavController,
    modifier: Modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
) {
    when (annotatedElement) {
        is ComicHtmlAnnotatedElement.Header -> {
            Text(
                text = annotatedElement.header,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier
            )
        }

        is ComicHtmlAnnotatedElement.Image -> {
            CoilImage(
                imageModel = { annotatedElement.imageUrl },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        is ComicHtmlAnnotatedElement.Text -> {
            Text(
                text = annotatedElement.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier
            )
        }

        is ComicHtmlAnnotatedElement.Title -> {
            Text(
                text = annotatedElement.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier
            )
        }

        is ComicHtmlAnnotatedElement.Url -> {
            Text(text = annotatedElement.urlText, style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            ), modifier = Modifier
                .padding(8.dp)
                .clickable {
                    Timber.tag("Clicked HTML URL: ${annotatedElement.urlLink}")
                    annotatedElement.urlLink?.let { urlLink ->
                        val formatedUrl = WEBVIEW_COMIC_VINE_URL + Utils.resolvePath(urlLink)

                        formatedUrl.also {
                            Timber.tag("Clicked HTML formated URL: $it")
                        }
                        navController.navigate(
                            Screen.WebViewScreen.route.plus("/${Uri.encode(formatedUrl)}")
                        )
                    }
                })
        }
    }
}