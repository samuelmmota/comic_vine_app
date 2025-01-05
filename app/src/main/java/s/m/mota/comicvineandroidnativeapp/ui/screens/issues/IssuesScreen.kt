package s.m.mota.comicvineandroidnativeapp.ui.screens.issues

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun IssuesScreen(
    navController: NavController,
) {
    val issueViewModel = hiltViewModel<IssueViewModel>()
    val sortSettings by issueViewModel.sortOrderSettings.collectAsStateWithLifecycle()
    val issueItems = issueViewModel.allIssuesUi.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        issueViewModel.allIssuesUi.collect {
            issueItems.refresh()
        }
    }

    IssuesListView(
        navController = navController,
        issueItems = issueItems,
        fetchSortEnum = sortSettings.first,
    )
}