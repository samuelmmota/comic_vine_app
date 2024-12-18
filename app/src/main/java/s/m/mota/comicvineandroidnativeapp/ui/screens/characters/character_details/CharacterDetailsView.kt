package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import s.m.mota.comicvineandroidnativeapp.R
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
                        baseColor = SecondaryFontColor, highlightColor = MaterialTheme.colorScheme.background
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
fun CharacterDetailsView(characterUi: ComicCharacterDetailsUi) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        AnnotatedHeaderContent(
            header = "Super Name:\t",
            content = characterUi.name,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyLarge
        )

        AnnotatedHeaderContent(
            header = "Real Name: ",
            content = characterUi.realName,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )


        AliasesSection(characterUi.aliases)
        CreatorsSection(characterUi.creators)
        PowersSection(characterUi.powers)

        AnnotatedHeaderContent(
            header = "First Appearance: ",
            content = characterUi.firstAppearedInIssue,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AliasesSection(aliases: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
    ) {
        Text(
            text = stringResource(R.string.aliases) + ":",
            style =  MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.width(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(aliases) { alias ->
                Text(
                    text = alias,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun CreatorsSection(creators: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
    ) {
        Text(
            text = stringResource(R.string.creators) + ":",
            style =  MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(creators) { creator ->
                Text(
                    text = creator,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun PowersSection(powers: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
    ) {
        Text(
            text = stringResource(R.string.powers) + ":",
            style =  MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(powers) { power ->
                Text(
                    text = power,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}