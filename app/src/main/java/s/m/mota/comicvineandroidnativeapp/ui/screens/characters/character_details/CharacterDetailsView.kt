package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.ComicCoilImage
import s.m.mota.comicvineandroidnativeapp.ui.component.HorizontalScrollableRowSection
import s.m.mota.comicvineandroidnativeapp.ui.component.RelatedComicResourceContentListView
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicCharacterDetailsUi

@Composable
fun CharacterDetailsImageView(imageUrl: String?, shareSiteDetailsUrl: String?, onFavoriteClick: () -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        ComicCoilImage(imageUrl= imageUrl,
            contentDescription = "Comic Character Details Image",
            modifier =  Modifier.fillMaxWidth())
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
fun CharacterCard(characterUi: ComicCharacterDetailsUi) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = characterUi.name ?: stringResource(R.string.unknown_information),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Character » ${characterUi.name ?: ""} appears in ${
                    characterUi.countOfIssueAppearances ?: stringResource(
                        R.string.unknown_information
                    )
                } issues.", style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = characterUi.deck ?: stringResource(R.string.unknown_information),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CharacterDetailsView(
    characterUi: ComicCharacterDetailsUi, navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "General Information", style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
        AnnotatedHeaderContent(
            header = "Super Name: ",
            content = characterUi.name ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Real Name: ",
            content = characterUi.realName ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        HorizontalScrollableRowSection(
            stringResource(R.string.aliases) + ":",
            characterUi.aliases ?: listOf(stringResource(R.string.unknown_information))
        )

        AnnotatedHeaderContent(
            header = "Publisher: ",
            content = characterUi.publisher ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Gender: ",
            content = characterUi.gender ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Character Type: ",
            content = characterUi.characterType ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Appears in ",
            content = characterUi.countOfIssueAppearances
                ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Birthday: ",
            content = characterUi.birth ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        characterUi.creatorsUi?.let { comicResource ->
            RelatedComicResourceContentListView(
                comicResourceItems = comicResource,
                title = stringResource(R.string.creators) + ":",
                navController = navController
            )
        }

        characterUi.firstAppearedInIssue?.let { comicResource ->
            RelatedComicResourceContentListView(
                comicResourceItems = listOf(comicResource),
                title = stringResource(R.string.first_appearance),
                navController = navController
            )
        }

        characterUi.issuesDiedIn?.let { comicResourceList ->
            RelatedComicResourceContentListView(
                comicResourceItems = comicResourceList,
                title = "Died in: ",
                navController = navController
            )
        }

        characterUi.powersUi?.let { comicResource ->
            RelatedComicResourceContentListView(
                comicResourceItems = comicResource,
                title = stringResource(R.string.powers) + ":",
                navController = navController
            )
        }
    }
}