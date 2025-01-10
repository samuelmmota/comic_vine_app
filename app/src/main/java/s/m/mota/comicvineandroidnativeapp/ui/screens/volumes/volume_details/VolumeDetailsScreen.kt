package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.component.text.DisplayComicHtmlAnnotatedElement
import s.m.mota.comicvineandroidnativeapp.ui.theme.FloatingActionBackground

@Composable
fun VolumeDetailsScreen(navController: NavController, volumeApiId: String) {
    val viewModel = hiltViewModel<VolumeDetailsViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val volumeDetails by viewModel.volumeDetails.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val parsedDescription by viewModel.parsedDescription.collectAsState()
    /*
    Use LaunchedEffect if:
    The parameter (volumeApiId) is only relevant to the UI composition.
    The data fetching logic depends on something transient in the @Composable function's scope (e.g., a navigation argument).
    The ViewModel is used in multiple screens with different contexts.
    This will happen when the user navigates between screens.
    LaunchedEffect(Unit) {
        viewModel.volumeDetails(volumeApiId)
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
                    volumeDetails?.let {
                        VolumeDetailsImageView(it.imageUrl, it.siteDetailUrl) {
                            //onFavoriteButtonClick action
                        }
                    }
                }
                item {
                    volumeDetails?.let {
                        VolumeDetailsView(navController, it)
                    }
                }

                parsedDescription?.let { descriptionElements ->
                    item {
                        Text(
                            text = stringResource(R.string.description),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )

                    }

                    descriptionElements.forEach { element ->
                        item {
                            DisplayComicHtmlAnnotatedElement(
                                annotatedElement = element,
                                navController = navController,
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
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

@Preview(name = "volumeDetails", showBackground = true)
@Composable
fun Preview() {
}