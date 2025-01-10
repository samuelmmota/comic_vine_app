package s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen.botton_navigation

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.navigation.currentRoute
import s.m.mota.comicvineandroidnativeapp.utils.extensions.singleTopNavigator

@Composable
fun BottomNavigationUI(navController: NavController, pagerState: PagerState) {
    NavigationBar {
        val items = if (pagerState.currentPage == 0) {
            listOf(
                Screen.IssuesNav,
                Screen.CharacterNav,
                Screen.VolumesNav,
                //Screen.MoviesNav,
                //Screen.PowersNav,
            )
        } else {
            listOf(
            )
        }
        items.forEachIndexed { index, item ->
            NavigationBarItem(icon = item.navIcon,
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                onClick = {
                    navController.singleTopNavigator(item.route)
                })
        }
    }
}
