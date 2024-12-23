package s.m.mota.comicvineandroidnativeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.theme.SecondaryFontColor
import s.m.mota.comicvineandroidnativeapp.ui.theme.cornerRadius
import s.m.mota.comicvineandroidnativeapp.utils.CircularRevealPluginDuration


@Composable
fun RelatedComicResourceContentListView(
    title: String,
    navController: NavController,
    comicResourceItems: List<ComicResourceUi>,
    gridFixedCells: Int = 1
) {
    val progressBar = remember { mutableStateOf(false) }
    val lazyListState = rememberLazyGridState()

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = title, style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), textAlign = TextAlign.Start
            )

            LazyHorizontalGrid(state = lazyListState,
                rows = GridCells.Fixed(gridFixedCells),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 5.dp, vertical = 8.dp),
                content = {
                    items(comicResourceItems) { item ->

                        RelatedComicResourceItem(item) {
                            item.apiId?.let { apiId ->
                                val navigateScreen = when (item.resourceType) {
                                    ComicResourceType.CHARACTER -> Screen.CharacterDetailsScreen
                                    ComicResourceType.ISSUE -> Screen.IssueDetailsScreen
                                    ComicResourceType.VOLUME -> Screen.VolumeDetailsScreen
                                    else -> null
                                }
                                navigateScreen?.let { screen ->
                                    navController.navigate(
                                        screen.route.plus("/${apiId}")
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }
}

@Composable
fun RelatedComicResourceItem(
    comicResourceItem: ComicResourceUi, onClick: () -> Unit
) {
    Column(modifier = Modifier
        .padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)
        .width(125.dp)
        .fillMaxHeight()
        .clickable { onClick.invoke() }) {
        CoilImage(
            modifier = Modifier
                .size(125.dp)
                .cornerRadius(10),
            imageModel = { comicResourceItem.thumbnailImageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Comic Resource related content",
                colorFilter = null,
            ),
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = CircularRevealPluginDuration
                )
                +ShimmerPlugin(
                    shimmer = Shimmer.Flash(
                        baseColor = SecondaryFontColor,
                        highlightColor = MaterialTheme.colorScheme.background
                    )
                )
            },
        )
        Text(
            text = comicResourceItem.name
                ?: (comicResourceItem.resourceType?.name + comicResourceItem.id.let { " [$it]" }),
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.Start),
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}