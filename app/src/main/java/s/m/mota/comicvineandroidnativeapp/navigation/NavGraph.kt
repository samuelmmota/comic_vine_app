package s.m.mota.comicvineandroidnativeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.CharactersScreen
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details.CharacterDetailsScreen
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.IssuesScreen
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details.IssueDetailsScreen
import s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.VolumesScreen
import s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details.VolumeDetailsScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = Screen.IssuesScreen.route) {
        // Characters
        composable(Screen.CharactersScreen.route) {
            CharactersScreen(
                navController = navController
            )
        }
        // Character Details
        composable(
            Screen.CharacterDetailsScreen.route.plus(Screen.CharacterDetailsScreen.objectPath),
            arguments = listOf(navArgument(Screen.CharacterDetailsScreen.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.character_details)
            val characterApiId = it.arguments?.getString(Screen.CharacterDetailsScreen.objectName)
            characterApiId?.let {
                CharacterDetailsScreen(
                    navController = navController, characterApiId
                )
            }
        }

        // Issues
        composable(Screen.IssuesScreen.route) {
            IssuesScreen(
                navController = navController
            )
        }
        // Issue Details
        composable(
            Screen.IssueDetailsScreen.route.plus(Screen.IssueDetailsScreen.objectPath),
            arguments = listOf(navArgument(Screen.IssueDetailsScreen.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.issue_details)
            val issueApiId = it.arguments?.getString(Screen.IssueDetailsScreen.objectName)
            issueApiId?.let {
                IssueDetailsScreen(
                    navController = navController, issueApiId
                )
            }
        }

        // Volumes
        composable(Screen.VolumesScreen.route) {
            VolumesScreen(
                navController = navController
            )
        }
        // Volume Details
        composable(
            Screen.VolumeDetailsScreen.route.plus(Screen.VolumeDetailsScreen.objectPath),
            arguments = listOf(navArgument(Screen.VolumeDetailsScreen.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.issue_details)
            val volumeApiId = it.arguments?.getString(Screen.VolumeDetailsScreen.objectName)
            volumeApiId?.let {
                VolumeDetailsScreen(
                    navController = navController, volumeApiId
                )
            }
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.CharactersScreen.route -> stringResource(id = R.string.characters)
        Screen.CharacterDetailsScreen.route -> stringResource(id = R.string.character_details)
        Screen.IssuesScreen.route -> stringResource(id = R.string.issues)
        Screen.IssueDetailsScreen.route -> stringResource(id = R.string.issue_details)
        Screen.VolumesScreen.route -> stringResource(id = R.string.volumes_page)
        Screen.VolumeDetailsScreen.route -> stringResource(id = R.string.volume_details)
        else -> {
            stringResource(R.string.app_name)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}