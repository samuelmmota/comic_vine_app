package s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.navigation.Navigation
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.navigation.currentRoute
import s.m.mota.comicvineandroidnativeapp.navigation.navigationTitle
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.component.ExitAlertDialog
import s.m.mota.comicvineandroidnativeapp.ui.component.SearchBar
import s.m.mota.comicvineandroidnativeapp.ui.component.SearchUI
import s.m.mota.comicvineandroidnativeapp.ui.component.SortSettingAlertDialog
import s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen.botton_navigation.BottomNavigationUI
import s.m.mota.comicvineandroidnativeapp.utils.networkconnection.ConnectionState
import s.m.mota.comicvineandroidnativeapp.utils.networkconnection.connectivityState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val pagerState = rememberPagerState { 1 }
    val openDialog = remember { mutableStateOf(false) }
    val isSortSettingsDialogVisible = remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)

    BackHandler(
        enabled = isAppBarVisible.value.not()
    ) {
        isAppBarVisible.value = true
    }

    BackHandler(
        enabled = currentRoute(navController) in listOf(
            Screen.IssuesScreen.route,
            Screen.CharactersScreen.route,
            Screen.VolumesScreen.route
        )
    ) {
        openDialog.value = true
    }

    Scaffold(topBar = {
        if (!isAppBarVisible.value) {
            SearchBar(isAppBarVisible, mainViewModel) {
                //onCloseClick
                isAppBarVisible.value = true
            }
        } else CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(
                text = navigationTitle(navController),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }, navigationIcon = {
            when (val activeScreen = currentRoute(navController)) {
                in listOf(
                    Screen.CharacterDetailsScreen.route,
                    Screen.IssueDetailsScreen.route,
                    Screen.VolumeDetailsScreen.route
                ) -> {
                    IconButton(onClick = {
                        /*if (activeScreen != Screen.IssuesScreen.route) {
                             //val route = if (pagerState.currentPage == ACTIVE_CHARACTERS_TAB) Screen.CharactersScreen.route else Screen.AiringTodayTvSeries.route
                             navController.navigate(Screen.IssuesScreen.route) {
                                 popUpTo(navController.graph.startDestinationId) {
                                     inclusive = true
                                 }
                             }
                         } else {
                             navController.popBackStack()
                         }*/
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                }
            }
        }, scrollBehavior = scrollBehavior, actions = {
            if (currentRoute(navController) !in listOf(
                    Screen.CharacterDetailsScreen.route,
                    Screen.IssueDetailsScreen.route,
                    Screen.VolumeDetailsScreen.route,
                )
            ) {
                IconButton(onClick = {
                    isSortSettingsDialogVisible.value = true
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "Sort List action button",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }

                IconButton(onClick = {
                    isAppBarVisible.value = false
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search action button",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        })
    }, floatingActionButton = {}, bottomBar = {
        when (currentRoute(navController)) {
            Screen.CharactersScreen.route, Screen.IssuesScreen.route, Screen.VolumesScreen.route -> {
                BottomNavigationUI(navController, pagerState)
            }
        }
    }, snackbarHost = {
        if (isConnected.not()) {
            Snackbar(
                action = {}, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(R.string.there_is_no_internet))
            }
        }
    }) {
        Box(Modifier.padding(it)) {
            MainView(navController, pagerState)
            CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
            if (isAppBarVisible.value.not()) {
                SearchUI(navController, mainViewModel.searchResultData) {
                    isAppBarVisible.value = true
                }/*mainViewModel.characterSearchData.pagingLoadingState {
                     searchProgressBar.value = it
                 }*/
            }
        }
    }
    // Exit App Dialog
    if (currentRoute(navController) in listOf(
            Screen.IssuesScreen.route,
            Screen.CharactersScreen.route,
            Screen.VolumesScreen.route
        ) && openDialog.value
    ) {
        ExitAlertDialog(title = stringResource(R.string.close_the_app),
            description = stringResource(R.string.do_you_want_to_exit_the_app),
            {
                openDialog.value = it
            },
            {
                activity?.finish()
            })
    }
    // Sort List Settings Dialog
    if (currentRoute(navController) !in listOf(
            Screen.CharacterDetailsScreen.route,
            Screen.IssueDetailsScreen.route,
            Screen.VolumeDetailsScreen.route,
        ) && isSortSettingsDialogVisible.value
    ) {
        SortSettingAlertDialog(viewmodel = mainViewModel, cancel = {
            isSortSettingsDialogVisible.value = it
        }, apply = {
            isSortSettingsDialogVisible.value = it
        })
    }
}

@Composable
fun MainView(
    navigator: NavHostController, pagerState: PagerState
) {
    Column {
        if (currentRoute(navigator) !in listOf(
                Screen.CharacterDetailsScreen.route,
            )
        ) {/*if (!isFavorite) {
                MovieTvSeriesTabView(navigator, pagerState)
            } else {
                FavoriteTabView(navigator)
            }*/
        }
        HorizontalPager(
            state = pagerState, modifier = Modifier.fillMaxSize()
        ) { page ->
            Navigation(navigator)
        }
    }
}