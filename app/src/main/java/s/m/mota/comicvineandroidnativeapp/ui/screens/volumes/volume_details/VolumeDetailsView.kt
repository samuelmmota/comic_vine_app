package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.component.ComicCoilImage
import s.m.mota.comicvineandroidnativeapp.ui.component.RelatedComicResourceContentListView
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicVolumeDetailsUi

@Composable
fun VolumeDetailsImageView(
    imageUrl: String?, shareSiteDetailsUrl: String?, onFavoriteClick: () -> Unit
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
fun VolumeCard(navController: NavController, volumeDetailsUi: ComicVolumeDetailsUi) {
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
                text = volumeDetailsUi.name ?: stringResource(R.string.unknown_information),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            val publishedByText = buildAnnotatedString {
                volumeDetailsUi.publisher?.let { publisher ->
                    append(stringResource(R.string.published_by)+" ")
                    addStringAnnotation(
                        tag = "URL",
                        annotation = publisher.siteDetailUrl.orEmpty(),
                        start = length - (publisher.name?.length ?: 0),
                        end = length
                    )
                    val siteUrl =
                        if (!publisher.siteDetailUrl.isNullOrEmpty()) {
                            publisher.siteDetailUrl
                        } else if (!publisher.alternativeSiteDetailUrl.isNullOrEmpty()) {
                            publisher.alternativeSiteDetailUrl
                        } else {
                            null
                        }
                    siteUrl?.let {
                        val link = LinkAnnotation.Url(
                            it,
                            TextLinkStyles(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                        ) {
                            val url = (it as LinkAnnotation.Url).url
                            navController.navigate(
                                Screen.WebViewScreen.route.plus("/${Uri.encode(url)}")
                            )
                        }
                        withLink(link) { append(publisher.name) }
                    }
                    append(". ")
                }
            }
            val startedInText = if (!volumeDetailsUi.startYear.isNullOrEmpty()) {
                "Started in  ${volumeDetailsUi.startYear}."
            } else ""
            Text(
                text = buildAnnotatedString {
                    append("VOLUME » ${volumeDetailsUi.name ?: ""}. ")
                    append(publishedByText)
                    append(startedInText)
                }, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            volumeDetailsUi.deck?.let { deck ->
                Text(
                    text = deck, style = MaterialTheme.typography.bodyMedium, fontSize = 14.sp
                )
            }
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
            content = volumeUi.publisher?.name ?: stringResource(R.string.unknown_information),
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