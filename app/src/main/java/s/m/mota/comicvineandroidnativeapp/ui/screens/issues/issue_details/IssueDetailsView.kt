package s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.ComicResourceUi
import s.m.mota.comicvineandroidnativeapp.ui.component.HorizontalScrollableRowSection
import s.m.mota.comicvineandroidnativeapp.ui.component.RelatedComicResourceContentListView
import s.m.mota.comicvineandroidnativeapp.ui.component.SlidingImageGalleryWithDots
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicIssueDetailsUi

@Composable
fun IssueDetailsImageView(imageList: List<String>, onFavoriteClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        SlidingImageGalleryWithDots(imageList)
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
fun IssueCard(issueDetailsUi: ComicIssueDetailsUi) {
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
                text = issueDetailsUi.name ?: stringResource(R.string.unknown_information),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${issueDetailsUi.volume?.name ?: ""} » ${issueDetailsUi.volume?.name ?: ""} #${issueDetailsUi.issueNumber ?: ""} - ${issueDetailsUi.name} released on ${
                    issueDetailsUi.coverDate ?: stringResource(
                        R.string.unknown_information
                    )
                }.", style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            issueDetailsUi.deck?.let { deck ->
                Text(
                    text = deck, style = MaterialTheme.typography.bodyMedium, fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun IssueDetailsView(issueUi: ComicIssueDetailsUi, navController: NavController) {

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
            header = "Issue Name:\t",
            content = issueUi.name ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
            ),
            contentStyle = MaterialTheme.typography.bodyLarge
        )
        AnnotatedHeaderContent(
            header = "Volume:\t",
            content = issueUi.volume?.name ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Issue Number: ",
            content = issueUi.issueNumber ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 10.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        VerificationSection(isVerified = issueUi.hasStaffReview)
        HorizontalScrollableRowSection(
            stringResource(R.string.aliases) + " :",
            issueUi.aliases ?: listOf(stringResource(R.string.unknown_information))
        )
        AnnotatedHeaderContent(
            header = "Cover Date: ",
            content = issueUi.coverDate ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Added Date: ",
            content = issueUi.dateAdded ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 10.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "In Store Date: ",
            content = issueUi.storeDate ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Resource Updated Date: ",
            content = issueUi.dateLastUpdated ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Resource Added Date: ",
            content = issueUi.dateAdded ?: stringResource(R.string.unknown_information),
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        issueUi.personCredits?.let { personCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = personCredits,
                title = "Creators",
                navController = navController
            )
        }

        issueUi.characterCredits?.let { characterCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = characterCredits,
                title = "Characters",
                navController = navController
            )
        }

        issueUi.teamCredits?.let { teamCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = teamCredits, title = "Teams", navController = navController
            )
        }

        issueUi.locationCredits?.let { locationCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = locationCredits,
                title = "Locations",
                navController = navController
            )
        }
        issueUi.conceptCredits?.let { conceptCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = conceptCredits,
                title = "Concepts",
                navController = navController
            )
        }
        issueUi.objectCredits?.let { locationCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = locationCredits,
                title = "Objects",
                navController = navController
            )
        }
        issueUi.storyArcCredits?.let { storyArcCredits ->
            RelatedComicResourceContentListView(
                comicResourceItems = storyArcCredits,
                title = "Story Arcs",
                navController = navController
            )
        }
        issueUi.charactersDiedIn?.let { charactersDiedIn ->
            RelatedComicResourceContentListView(
                comicResourceItems = charactersDiedIn,
                title = "Characters Died In",
                navController = navController
            )
        }
        issueUi.teamsDisbandedIn?.let { teamsDisbandedIn ->
            RelatedComicResourceContentListView(
                comicResourceItems = teamsDisbandedIn,
                title = "Teams Disbanded In",
                navController = navController
            )
        }
        issueUi.disbandedTeams?.let { teamsDisbandedIn ->
            RelatedComicResourceContentListView(
                comicResourceItems = teamsDisbandedIn,
                title = "Disbanded Teams",
                navController = navController
            )
        }
        issueUi.firstAppearanceCharacters?.let { firstAppearanceCharacters ->
            RelatedComicResourceContentListView(
                comicResourceItems = firstAppearanceCharacters,
                title = "First Appearance Characters",
                navController = navController
            )
        }
        issueUi.firstAppearanceConcepts?.let { firstAppearanceConcepts ->
            RelatedComicResourceContentListView(
                comicResourceItems = firstAppearanceConcepts,
                title = "First Appearance Concepts",
                navController = navController
            )
        }
        issueUi.firstAppearanceLocations?.let { firstAppearanceLocations ->
            RelatedComicResourceContentListView(
                comicResourceItems = firstAppearanceLocations,
                title = "First Appearance Locations",
                navController = navController
            )
        }
        issueUi.firstAppearanceStoryArcs?.let { firstAppearanceStoryArcs ->
            RelatedComicResourceContentListView(
                comicResourceItems = firstAppearanceStoryArcs,
                title = "First Appearance Story Arcs",
                navController = navController
            )
        }
        issueUi.firstAppearanceTeams?.let { firstAppearanceTeams ->
            RelatedComicResourceContentListView(
                comicResourceItems = firstAppearanceTeams,
                title = "First Appearance Teams",
                navController = navController
            )
        }
    }
}

@Composable
fun VerificationSection(isVerified: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Verified: ", style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            )
        )

        Icon(
            imageVector = if (isVerified) Icons.Filled.CheckCircle else Icons.Filled.Close,
            contentDescription = if (isVerified) "Verified" else "Not Verified",
            tint = if (isVerified) Color.Green else Color.Red,
            modifier = Modifier
                .size(20.dp)
                .padding(end = 4.dp)
        )

        Text(
            text = if (isVerified) "Yes" else "No",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isVerified) Color.Green else Color.Red
            )
        )
    }
}
