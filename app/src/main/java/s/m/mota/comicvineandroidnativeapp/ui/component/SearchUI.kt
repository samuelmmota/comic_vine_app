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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import s.m.mota.comicvineandroidnativeapp.data.model.response.ResourceType
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.theme.SecondaryFontColor
import s.m.mota.comicvineandroidnativeapp.ui.theme.cornerRadius
import s.m.mota.comicvineandroidnativeapp.utils.CircularRevealPluginDuration
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
                                        ResourceType.CHARACTER -> {
                                            item.getCharacterApiId()?.let { characterApiId ->
                                                navController.navigate(Screen.CharacterDetailsScreen.route.plus("/$characterApiId"))
                                            }
                                        }
                                        else -> {}
                                    }
                                }
                        }) {
                        CoilImage(
                            modifier = Modifier
                                .height(100.dp)
                                .width(80.dp)
                                .cornerRadius(8),
                            imageModel = {
                                item.image?.smallUrl ?: item.image?.iconUrl ?: item.image?.iconUrl
                                ?: ""
                            },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                                contentDescription = "search item",
                                colorFilter = null,
                            ),
                            component = rememberImageComponent {
                                +CircularRevealPlugin(
                                    duration = CircularRevealPluginDuration
                                )
                                +ShimmerPlugin(
                                    shimmer = Shimmer.Flash(
                                        baseColor = SecondaryFontColor,
                                        highlightColor = MaterialTheme.colorScheme.background
                                    )
                                )
                            },
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
                                    ResourceType.CHARACTER -> {
                                        item.countOfIssueAppearances?.let { issueCount ->
                                            Text(
                                                text = "$issueCount Issues",
                                                color = MaterialTheme.colorScheme.secondary,
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    ResourceType.VOLUME -> {
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

                                    ResourceType.SERIES -> {
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