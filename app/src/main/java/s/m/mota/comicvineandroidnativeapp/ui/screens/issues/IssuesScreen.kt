package s.m.mota.comicvineandroidnativeapp.ui.screens.issues

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun IssuesScreen(
    navController: NavController,
) {
    val issueViewModel = hiltViewModel<IssueViewModel>()
    IssuesListView(
        navController = navController,
        issueItems = issueViewModel.allIssues.collectAsLazyPagingItems(),
    )
}