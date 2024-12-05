package s.m.mota.comicvineandroidnativeapp.ui.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AnnotatedHeaderContent(
    header: String,
    content: String,
    maxLines: Int = 2,
    headerStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    ),
    contentStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onSurface
    ),
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = headerStyle.toSpanStyle()
        ) {
            append(header)
        }
        withStyle(
            style = contentStyle.toSpanStyle()
        ) {
            append(content)
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier
            .padding(bottom = 8.dp)
            .wrapContentSize(Alignment.Center),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}