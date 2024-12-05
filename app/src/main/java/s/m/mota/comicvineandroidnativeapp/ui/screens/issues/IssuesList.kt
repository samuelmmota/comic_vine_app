package s.m.mota.comicvineandroidnativeapp.ui.screens.issues

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.data.model.ComicIssue
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.theme.DefaultBackgroundColor
import s.m.mota.comicvineandroidnativeapp.ui.theme.FloatingActionBackground
import s.m.mota.comicvineandroidnativeapp.ui.theme.SecondaryFontColor
import s.m.mota.comicvineandroidnativeapp.ui.theme.cornerRadius
import s.m.mota.comicvineandroidnativeapp.utils.CircularRevealPluginDuration
import s.m.mota.comicvineandroidnativeapp.utils.extensions.items
import s.m.mota.comicvineandroidnativeapp.utils.extensions.pagingLoadingState

@Composable
fun IssuesListView(
    navController: NavController,
    issueItems: LazyPagingItems<ComicIssue>,
) {
    val progressBar = remember { mutableStateOf(false) }
    val lazyListState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }

    Box(modifier = Modifier.background(DefaultBackgroundColor)) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        LazyVerticalGrid(state = lazyListState,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .padding(top = 8.dp),
            content = {
                items(issueItems) { item ->
                    item?.let {
                        IssueListItem(item, navController)
                    }
                }
            })
        if (showButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }, modifier = Modifier
                    .cornerRadius(30)
                    .align(Alignment.BottomEnd),
                //.padding(16.dp),
                containerColor = FloatingActionBackground
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Scroll to top",
                    tint = Color.White,
                )
            }
        }
    }
    issueItems.pagingLoadingState {
        progressBar.value = it
    }
}


@Composable
fun IssueListItem(item: ComicIssue, navController: NavController) {
    Column(modifier = Modifier
        .padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)
        .clickable {
            /*item.getCharacterApiId()
                ?.let { characterApiId ->
                    navController.navigate(Screen.CharacterDetails.route.plus("/$characterApiId"))
                }  */
        }) {
        Text(
            text = item.name ?: "No Name",
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        CoilImage(
            modifier = Modifier
                .size(250.dp)
                .cornerRadius(10),
            //.clickable {},
            imageModel = { item.image!!.superUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Character List item",
                colorFilter = null,
            ),
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = CircularRevealPluginDuration
                )
                +ShimmerPlugin(
                    shimmer = Shimmer.Flash(
                        baseColor = SecondaryFontColor, highlightColor = DefaultBackgroundColor
                    )
                )
            },
        )
        item.id?.let { text ->
            AnnotatedHeaderContent(header = "ID: ", content = text.toString())
        }
        item.getAliasesAsString()?.let { text ->
            AnnotatedHeaderContent(header = "Aliases: ", content = text, maxLines = 4)
        }
        item.countOfIssueAppearances?.let { text ->
            AnnotatedHeaderContent(header = "Issue count: ", content = text.toString())
        }
        item.publisher?.name?.let { text ->
            AnnotatedHeaderContent(
                header = "Publisher:", content = text
            )
        }
    }
}

@Composable
fun AnnotatedHeaderContent(
    header: String, content: String, maxLines: Int = 2, modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ).toSpanStyle()
        ) {
            append(header)
        }
        withStyle(
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ).toSpanStyle()
        ) {
            append(content)
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier
            .padding(bottom = 5.dp)
            .wrapContentSize(Alignment.Center),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}