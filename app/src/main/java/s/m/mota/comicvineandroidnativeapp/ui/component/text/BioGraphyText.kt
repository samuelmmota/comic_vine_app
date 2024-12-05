package s.m.mota.comicvineandroidnativeapp.ui.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import s.m.mota.comicvineandroidnativeapp.ui.theme.bioGrapyText

@Composable
fun BioGraphyText(text: String) {
    Text(
        text = text, style = MaterialTheme.typography.bioGrapyText
    )
}

val bioGrapyText: TextStyle
    @Composable get() {
        return MaterialTheme.typography.bioGrapyText
    }