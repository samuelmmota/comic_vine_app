package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun VolumesScreen(
    navController: NavController,
) {
    val volumeViewModel = hiltViewModel<VolumeViewModel>()
    VolumesListView(
        navController = navController,
        volumeItems = volumeViewModel.allVolumesUi.collectAsLazyPagingItems(),
    )
}