package s.m.mota.comicvineandroidnativeapp.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import s.m.mota.comicvineandroidnativeapp.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    data object CharactersScreen : Screen(route = Routes.CHARACTERS_SCREEN_ROUTE)
    data object IssuesScreen : Screen(route = Routes.ISSUES_SCREEN_ROUTE)

    data object CharacterDetailsScreen : Screen(
        route = Routes.CHARACTER_DETAILS_ROUTE,
        objectName = "characterApiId",
        objectPath = "/{characterApiId}"
    )
    data object IssueDetailsScreen : Screen(
        route = Routes.ISSUE_DETAILS_SCREEN_ROUTE,
        objectName = "issueApiId",
        objectPath = "/{issueApiId}"
    )

    // Bottom Navigation
    data object CharacterNav :
        Screen(Routes.CHARACTERS_SCREEN_ROUTE, title = R.string.characters_page, navIcon = {
            Icon(
                Icons.Filled.Face,
                contentDescription = "search",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .offset(x = 10.dp)
            )
        })

    data object IssuesNav :
        Screen(Routes.ISSUES_SCREEN_ROUTE, title = R.string.issues_page, navIcon = {
            Icon(
                Icons.AutoMirrored.Filled.List,
                contentDescription = "search",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .offset(x = 10.dp)
            )
        })

    data object VolumesNav :
        Screen(Routes.VOLUMES_SCREEN_ROUTE, title = R.string.volumes_page, navIcon = {
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "search",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .offset(x = 10.dp)
            )
        })

    data object MoviesNav :
        Screen(Routes.MOVIES_SCREEN_ROUTE, title = R.string.movies_page, navIcon = {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = "search",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .offset(x = 10.dp)
            )
        })

    data object PowersNav :
        Screen(Routes.POWERS_SCREEN_ROUTE, title = R.string.powers_page, navIcon = {
            Icon(
                Icons.Filled.Star,
                contentDescription = "powers",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .offset(x = 10.dp)
            )
        })
}