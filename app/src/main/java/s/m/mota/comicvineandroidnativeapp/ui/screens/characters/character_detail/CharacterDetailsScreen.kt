package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_detail

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.theme.DefaultBackgroundColor
import s.m.mota.comicvineandroidnativeapp.ui.theme.FloatingActionBackground

@Composable
fun CharacterDetailsScreen(navController: NavController, characterApiId: String) {
    val viewModel = hiltViewModel<CharacterDetailsViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val characterDetail by viewModel.characterDetails.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.characterDetail(characterApiId)
    }

    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultBackgroundColor)
    ) {
        LazyColumn(
            state = lazyListState, modifier = Modifier.fillMaxSize()
        ) {
            item {
                CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f)
            }
            if (isLoading.not()) {
                item {
                    CharacterDetailsImageView(viewModel.toComicCharacter(characterDetail).imageUrl) {
                        //onFavoriteButtonClick action
                    }
                }
                item {
                    CharacterDetailsView(viewModel.toComicCharacter(characterDetail))
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