package s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.component.SlidingImageGalleryWithDots
import s.m.mota.comicvineandroidnativeapp.ui.component.text.AnnotatedHeaderContent

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
fun IssueDetailsView(issueUi: ComicIssueDetailsUi) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        AnnotatedHeaderContent(
            header = "Issue Name:\t",
            content = issueUi.name,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        VerificationSection(isVerified = issueUi.hasStaffReview)
        AliasesSection(issueUi.aliases)
        AnnotatedHeaderContent(
            header = "Issue Id: ",
            content = issueUi.id,
            modifier = Modifier.padding(top = 10.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Cover Date: ",
            content = issueUi.coverDate,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Added Date: ",
            content = issueUi.dateAdded,
            modifier = Modifier.padding(top = 10.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )
        AnnotatedHeaderContent(
            header = "Updated Date: ",
            content = issueUi.dateLastUpdated,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Deck: ",
            content = issueUi.deck,
            modifier = Modifier.padding(top = 5.dp),
            headerStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
            ),
            contentStyle = MaterialTheme.typography.bodyMedium
        )

        AnnotatedHeaderContent(
            header = "Added Date: ",
            content = issueUi.dateAdded,
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
            color = MaterialTheme.colorScheme.primary,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
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
fun VerificationSection(isVerified: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Verified: ",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
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
