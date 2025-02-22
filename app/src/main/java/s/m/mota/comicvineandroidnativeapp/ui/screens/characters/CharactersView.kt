package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchSortSetting
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.component.ComicCoilImage
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.ui.theme.FloatingActionBackground
import s.m.mota.comicvineandroidnativeapp.ui.theme.cornerRadius
import s.m.mota.comicvineandroidnativeapp.utils.extensions.items
import s.m.mota.comicvineandroidnativeapp.utils.extensions.pagingLoadingState

@Composable
fun CharactersListView(
    navController: NavController,
    characterItems: LazyPagingItems<ComicCharacterUi>,
    fetchSortEnum: FetchSortSetting
) {
    val progressBar = remember { mutableStateOf(false) }
    val lazyListState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        LazyVerticalGrid(state = lazyListState,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .padding(top = 8.dp),
            content = {
                items(characterItems) { item ->
                    item?.let {
                        CharacterListItem(item, navController, fetchSortEnum)
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
    characterItems.pagingLoadingState {
        progressBar.value = it
    }
}


@Composable
fun CharacterListItem(
    item: ComicCharacterUi, navController: NavController, fetchSortEnum: FetchSortSetting
) {
    Column(modifier = Modifier
        .padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)
        .clickable {
            item.characterApiId?.let { characterApiId ->
                navController.navigate(Screen.CharacterDetailsScreen.route.plus("/$characterApiId"))
            }
        }) {
        Text(
            text = item.name
                ?: (stringResource(R.string.unknown_character) + item.id.let { " [$it]" }),
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
        ComicCoilImage(imageUrl = item.imageUrl,
            contentDescription = "Character List item",
            modifier = Modifier
                .size(250.dp)
                .cornerRadius(10),
            //.clickable {}
            )

        when (fetchSortEnum) {
            FetchSortSetting.ID -> {
                AnnotatedHeaderContent(
                    header = "ID: ",
                    content = item.id ?: stringResource(R.string.unknown_information)
                )
            }

            FetchSortSetting.DATE_ADDED -> {
                AnnotatedHeaderContent(
                    header = "Added Date: ",
                    content = item.dateAdded ?: stringResource(R.string.unknown_information)
                )
            }

            FetchSortSetting.DATE_LAST_UPDATED -> {
                AnnotatedHeaderContent(
                    header = "Update Date: ",
                    content = item.dateLastUpdated ?: stringResource(R.string.unknown_information)
                )
            }
        }

        AnnotatedHeaderContent(
            header = "Publisher:",
            content = item.publisher ?: stringResource(R.string.unknown_information)
        )
        item.aliases?.let { aliases ->
            AnnotatedHeaderContent(header = "Aliases: ", content = aliases, maxLines = 4)
        }
    }
}