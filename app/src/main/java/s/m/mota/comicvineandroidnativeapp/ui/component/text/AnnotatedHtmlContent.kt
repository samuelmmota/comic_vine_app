package s.m.mota.comicvineandroidnativeapp.ui.component.text

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.utils.ComicHtmlElement
import s.m.mota.comicvineandroidnativeapp.utils.Utils
import s.m.mota.comicvineandroidnativeapp.utils.WEBVIEW_COMIC_VINE_URL

@Composable
fun AnnotatedHtmlContent(
    title: String,
    annotatedString: AnnotatedString,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .padding(top = 10.dp, bottom = 10.dp)
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title, style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
        ClickableText(text = annotatedString,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        val formatedUrl =
                            WEBVIEW_COMIC_VINE_URL + Utils.resolvePath(annotation.item)

                        formatedUrl.also {
                            println("Clicked URL: $it")
                        }
                        navController.navigate(
                            Screen.WebViewScreen.route.plus("/${Uri.encode(formatedUrl)}")
                        )
                    }
            })
    }
}

@Composable
fun DisplayHtmlContent(
    title: String,
    annotatedElements: List<ComicHtmlElement>,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .padding(top = 10.dp, bottom = 10.dp)
) {
    Column {
        Text(
            text = title, style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
        annotatedElements.forEach { element ->
            element.text?.let { text ->
                ClickableText(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    onClick = { offset ->
                        text.getStringAnnotations("URL", offset, offset).firstOrNull()
                            ?.let { annotation ->
                                val formatedUrl =
                                    WEBVIEW_COMIC_VINE_URL + Utils.resolvePath(annotation.item)

                                formatedUrl.also {
                                    println("Clicked URL: $it")
                                }
                                navController.navigate(
                                    Screen.WebViewScreen.route.plus("/${Uri.encode(formatedUrl)}")
                                )
                            }
                    }
                )
            }
            element.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}
