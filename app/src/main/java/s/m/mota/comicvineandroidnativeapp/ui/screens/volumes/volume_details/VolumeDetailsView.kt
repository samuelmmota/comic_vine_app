package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.ComicCoilImage
import s.m.mota.comicvineandroidnativeapp.ui.component.HorizontalScrollableRowSection
import s.m.mota.comicvineandroidnativeapp.ui.component.RelatedComicResourceContentListView
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicVolumeDetailsUi

@Composable
fun VolumeDetailsImageView(
    imageUrl: String?,
    shareSiteDetailsUrl: String?,
    onFavoriteClick: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        ComicCoilImage(
            imageUrl = imageUrl,
            contentDescription = "Comic Volume Details Image",
            modifier = Modifier.fillMaxWidth()
        )
        IconButton(
            onClick = {
                shareSiteDetailsUrl?.let {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, it)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Share Page button",
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = Color.Red
            )
        }
    }
}

@Composable
fun VolumeDetailsView(navController: NavController, volumeUi: ComicVolumeDetailsUi) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        AnnotatedHeaderContent(
            header = "volume Name:\t",
            content = volumeUi.name ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Volume Id: ",
            content = volumeUi.id ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 10.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Added Date: ",
            content = volumeUi.dateAdded ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Updated Date: ",
            content = volumeUi.dateLastUpdated ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Number of Issues: ",
            content = volumeUi.countOfIssues ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Publisher: ",
            content = volumeUi.publisher ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Started Year: ",
            content = volumeUi.startYear ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Deck: ",
            content = volumeUi.deck ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        volumeUi.locationCreditsUi?.let { locationCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = locationCredits,
                title = "Location Credits:",
                navController = navController
            )
        }

        volumeUi.personCreditsUi?.let { personCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = personCredits,
                title = "Person Credits :",
                navController = navController
            )
        }

        volumeUi.characterCreditsUi?.let { characterCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = characterCredits,
                title = "Character Credits :",
                navController = navController
            )
        }

        volumeUi.teamCreditsUi?.let { teamCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = teamCredits,
                title = "Team Credits:",
                navController = navController
            )
        }

        volumeUi.objectCreditsUi?.let { objectCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = objectCredits,
                title = "Object Credits:",
                navController = navController
            )
        }

        volumeUi.teamCreditsUi?.let { teamCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = teamCredits,
                title = "Team Credits:",
                navController = navController
            )
        }
    }
}