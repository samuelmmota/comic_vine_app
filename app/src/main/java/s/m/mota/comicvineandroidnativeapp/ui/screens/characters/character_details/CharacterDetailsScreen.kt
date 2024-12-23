package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHtmlContent
import s.m.mota.comicvineandroidnativeapp.ui.theme.FloatingActionBackground

@Composable
fun CharacterDetailsScreen(navController: NavController, characterApiId: String) {
    val viewModel: CharacterDetailsViewModel = hiltViewModel<CharacterDetailsViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val characterDetail by viewModel.characterDetails.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val parsedDescription by viewModel.parsedDescription.collectAsState()
    /*
    Use LaunchedEffect if:
    The parameter (characterApiId) is only relevant to the UI composition.
    The data fetching logic depends on something transient in the @Composable function's scope (e.g., a navigation argument).
    The ViewModel is used in multiple screens with different contexts.
    This will happen when the user navigates between screens.
    LaunchedEffect(Unit) {
        viewModel.characterDetail(characterApiId)
    }*/

    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            state = lazyListState, modifier = Modifier.fillMaxSize()
        ) {
            item {
                CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f)
            }
            if (isLoading.not()) {
                item {
                    characterDetail?.let {
                        CharacterDetailsImageView(it.imageUrl) {
                            //onFavoriteButtonClick action
                        }
                    }
                }

                item {
                    characterDetail?.let {
                        CharacterCard(it)
                    }
                }

                item {
                    characterDetail?.let {
                        CharacterDetailsView(it, firstApperanceOnClick = {

                        })
                    }
                }
                item {
                    parsedDescription?.let {
                        AnnotatedHtmlContent(
                            stringResource(R.string.description), (it)
                        )
                    }
                }
            }
        }
        if (showButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    //.cornerRadius(30)
                    //.clip(CircleShape)
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
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
}

@Preview(name = "CharacterDetails", showBackground = true)
@Composable
fun Preview() {
}