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
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_detail.CharacterDetailsScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = Screen.CharactersScreen.route) {
        // Characters
        composable(Screen.CharactersScreen.route) {
            CharactersScreen(
                navController = navController
            )
        }
        // Character Details
        composable(
            Screen.CharacterDetails.route.plus(Screen.CharacterDetails.objectPath),
            arguments = listOf(navArgument(Screen.CharacterDetails.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.character_details)
            val characterApiId = it.arguments?.getString(Screen.CharacterDetails.objectName)
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
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.CharactersScreen.route -> stringResource(id = R.string.app_name)
        Screen.CharacterDetails.route -> stringResource(id = R.string.character_details)
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