package s.m.mota.comicvineandroidnativeapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.theme.SecondaryFontColor
import s.m.mota.comicvineandroidnativeapp.utils.CircularRevealPluginDuration

@Composable
fun ComicCoilImage(imageUrl: String?, contentDescription: String, modifier: Modifier = Modifier) {
    CoilImage(modifier = modifier,
        imageModel = { imageUrl ?: R.drawable.comic_resource_original_blank_image },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            contentDescription = contentDescription
        ),
        component = rememberImageComponent {
            +CircularRevealPlugin(duration = CircularRevealPluginDuration)
            +ShimmerPlugin(
                shimmer = Shimmer.Flash(
                    baseColor = SecondaryFontColor,
                    highlightColor = MaterialTheme.colorScheme.background
                )
            )
        },
        loading = {
            Image(
                painter = painterResource(id = R.drawable.comic_resource_original_blank_image),
                contentDescription = "Loading Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        },
        failure = {
            Image(
                painter = painterResource(id = R.drawable.comic_resource_original_blank_image),
                contentDescription = "Fallback Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        })
}