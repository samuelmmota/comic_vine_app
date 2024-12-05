package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun CharactersScreen(
    navController: NavController,
) {
    val characterViewModel = hiltViewModel<CharactersViewModel>()
    CharactersListView(
        navController = navController,
        characterItems = characterViewModel.allCharacters.collectAsLazyPagingItems(),
    )
}