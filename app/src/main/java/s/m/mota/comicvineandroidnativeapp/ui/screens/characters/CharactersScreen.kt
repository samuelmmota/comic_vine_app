package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun CharactersScreen(
    navController: NavController,
) {
    val characterViewModel = hiltViewModel<CharactersViewModel>()
    val sortSettings by characterViewModel.sortOrderSettings.collectAsStateWithLifecycle()
    val characterItems = characterViewModel.allCharactersUi.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        characterViewModel.allCharactersUi.collect {
            characterItems.refresh()
        }
    }

    CharactersListView(
        navController = navController,
        characterItems = characterItems,
        fetchSortEnum = sortSettings.first,
    )
}