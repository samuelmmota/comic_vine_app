package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun VolumesScreen(
    navController: NavController,
) {
    val volumeViewModel = hiltViewModel<VolumeViewModel>()
    val sortSettings by volumeViewModel.sortOrderSettings.collectAsStateWithLifecycle()
    val volumeItems = volumeViewModel.allVolumesUi.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        volumeViewModel.allVolumesUi.collect {
            volumeItems.refresh()
        }
    }

    VolumesListView(
        navController = navController,
        volumeItems = volumeItems,
        fetchSortEnum = sortSettings.first,
    )
}