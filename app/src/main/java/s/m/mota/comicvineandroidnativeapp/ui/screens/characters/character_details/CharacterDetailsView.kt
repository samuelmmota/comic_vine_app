package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.HorizontalScrollableRowSection
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent
import s.m.mota.comicvineandroidnativeapp.ui.theme.SecondaryFontColor
import s.m.mota.comicvineandroidnativeapp.utils.CircularRevealPluginDuration

@Composable
fun CharacterDetailsImageView(imageUrl: String, onFavoriteClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        CoilImage(modifier = Modifier.fillMaxWidth(),
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Comic Character Details Image"
            ),
            component = rememberImageComponent {
                +CircularRevealPlugin(duration = CircularRevealPluginDuration)
                +ShimmerPlugin(
                    shimmer = Shimmer.Flash(
                        baseColor = SecondaryFontColor,
                        highlightColor = MaterialTheme.colorScheme.background
                    )
                )
            })
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
                text = "Character » ${characterUi.name ?: ""} appears in ${characterUi.countOfIssueAppearances ?: stringResource(R.string.unknown_information)} issues.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = characterUi.deck?: stringResource(R.string.unknown_information),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CharacterDetailsView(characterUi: ComicCharacterDetailsUi, firstApperanceOnClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "General Information",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
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

        HorizontalScrollableRowSection(
            stringResource(R.string.creators) + ":",
            characterUi.creators ?: listOf(stringResource(R.string.unknown_information))
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
            header = "First Appearance: ",
            content = characterUi.firstAppearedInIssue ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium,
        ){
            firstApperanceOnClick()
        }

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
            content = characterUi.birth
                ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        HorizontalScrollableRowSection(
            "Died in :",
            characterUi.issuesDiedIn ?: listOf(stringResource(R.string.unknown_information))
        )

        HorizontalScrollableRowSection(
            stringResource(R.string.powers) + ":",
            characterUi.powers ?: listOf(stringResource(R.string.unknown_information))
        )
    }
}