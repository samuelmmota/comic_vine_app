package s.m.mota.comicvineandroidnativeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.theme.cornerRadius
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState

@Composable
fun SearchUI(
    navController: NavController,
    searchData: MutableState<DataState<List<SearchResultModel>>?>,
    itemClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .heightIn(0.dp, 350.dp) // define max height
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 8.dp)

    ) {
        searchData.value?.let {
            if (it is DataState.Success<List<SearchResultModel>>) {
                items(items = it.data, itemContent = { item ->
                    Row(modifier = Modifier
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                        .clickable {
                            itemClick.invoke()
                            item
                                .getResourceType()
                                ?.let { resourceType ->
                                    when (resourceType) {
                                        ComicResourceType.CHARACTER -> {
                                            item.resourceApiId?.let { characterApiId ->
                                                navController.navigate(
                                                    Screen.CharacterDetailsScreen.route.plus(
                                                        "/$characterApiId"
                                                    )
                                                )
                                            }
                                        }

                                        ComicResourceType.ISSUE -> {
                                            item.resourceApiId?.let { issueApiId ->
                                                navController.navigate(
                                                    Screen.IssueDetailsScreen.route.plus(
                                                        "/$issueApiId"
                                                    )
                                                )
                                            }
                                        }

                                        ComicResourceType.VOLUME -> {
                                            item.resourceApiId?.let { volumeApiId ->
                                                navController.navigate(
                                                    Screen.VolumeDetailsScreen.route.plus(
                                                        "/$volumeApiId"
                                                    )
                                                )
                                            }
                                        }

                                        else -> {}
                                    }
                                }
                        }) {
                        ComicCoilImage(
                            imageUrl = item.image?.smallUrl ?: item.image?.iconUrl
                            ?: item.image?.iconUrl,
                            contentDescription = "Comic Search item", modifier = Modifier
                                .height(100.dp)
                                .width(80.dp)
                                .cornerRadius(8)
                        )

                        Column {
                            Text(
                                text = item.name ?: "",
                                modifier = Modifier.padding(
                                    start = 8.dp, top = 4.dp
                                ),
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = item.resourceType ?: "",
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            item.getResourceType()?.let { resourceType ->
                                when (resourceType) {
                                    ComicResourceType.CHARACTER -> {
                                        item.countOfIssueAppearances?.let { issueCount ->
                                            Text(
                                                text = "$issueCount Issues",
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    ComicResourceType.VOLUME -> {
                                        item.countOfIssues?.let { issueCount ->
                                            Text(
                                                text = "$issueCount Issues",
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                        item.startYear?.let { startYear ->
                                            Text(
                                                text = "Started in $startYear",
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    ComicResourceType.SERIES -> {
                                        item.countOfEpisodes?.let { episodeCount ->
                                            Text(
                                                text = "$episodeCount Episodes",
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                        item.startYear?.let { startYear ->
                                            Text(
                                                text = "Started in $startYear",
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    else -> {}
                                }
                                item.publisher?.name?.let { publisherName ->
                                    Text(
                                        text = publisherName,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                })
            }
        }
    }
}