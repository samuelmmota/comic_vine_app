package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_detail

import android.widget.TextView
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCreator
import s.m.mota.comicvineandroidnativeapp.data.model.character.Power
import s.m.mota.comicvineandroidnativeapp.ui.component.CircularIndeterminateProgressBar
import s.m.mota.comicvineandroidnativeapp.ui.theme.DefaultBackgroundColor
import s.m.mota.comicvineandroidnativeapp.ui.theme.FloatingActionBackground
import s.m.mota.comicvineandroidnativeapp.ui.theme.SecondaryFontColor
import s.m.mota.comicvineandroidnativeapp.utils.CircularRevealPluginDuration

@Composable
fun CharacterDetailScreen2(navController: NavController, characterApiId: String) {
    val viewModel = hiltViewModel<CharacterDetailViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val characterDetail by viewModel.characterDetail.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.characterDetail(characterApiId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                DefaultBackgroundColor
            )
    ) {
        CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f)
        characterDetail?.let { character ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    CoilImage(
                        modifier = Modifier.fillMaxWidth(),
                        imageModel = { character.image?.superUrl!! },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            contentDescription = "Comic Character Details",
                            colorFilter = null,
                        ),
                        component = rememberImageComponent {
                            +CircularRevealPlugin(
                                duration = CircularRevealPluginDuration
                            )
                            +ShimmerPlugin(
                                shimmer = Shimmer.Flash(
                                    baseColor = SecondaryFontColor,
                                    highlightColor = DefaultBackgroundColor
                                )
                            )
                        },
                    )
                    IconButton(
                        onClick = {},
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
                    } ?: Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.Gray
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AnnotatedHeaderContent(
                    header = "Super Name: \t",
                    content = character.name ?: "No Super Name",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                AnnotatedHeaderContent(
                    header = "Real Name: \t",
                    content = character.realName ?: "No Real Name",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                character.getAliasesAsList()?.let { aliasList ->
                    AliasesSection(aliasList)
                }

                AnnotatedHeaderContent(
                    header = "Publisher: \t",
                    content = character.publisher?.name ?: "No Publisher Name",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                character.creators?.let {
                    CreatorsSection(it)
                }

                AnnotatedHeaderContent(
                    header = "Gender: \t",
                    content = character.getGender(),
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                AnnotatedHeaderContent(
                    header = "Character Type: \t",
                    content = character.origin?.name ?: "No Character Type",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                AnnotatedHeaderContent(
                    header = "First Appearance In: \t",
                    content = character.firstAppearedInIssue?.name ?: "Unavailable Info",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                AnnotatedHeaderContent(
                    header = "Appears In: \t",
                    content = if (character.countOfIssueAppearances != null) {
                        "${character.countOfIssueAppearances} Issues"
                    } else {
                        "Unavailable Info"
                    },
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                AnnotatedHeaderContent(
                    header = "Birthday: \t",
                    content = character.birth ?: "Unavailable Info",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                AnnotatedHeaderContent(
                    header = "Died In: \t",
                    content = "Issue nº ${character.issuesDiedIn?.last()?.issueNumber ?: "Unavailable Info"} ${character.issuesDiedIn?.last()?.name ?: "Unavailable Info"}",
                    modifier = Modifier.padding(top = 10.dp),
                    headerStyle = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    contentStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                character.powers?.let {
                    PowersSection(it)
                }

                Text(
                    text = stringResource(R.string.summary),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Html(text = character.deck ?: "No summary Available")

                Text(
                    text = stringResource(R.string.description),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Html(text = character.description ?: "No description Available")

            }
        }
    }
}

@Composable
fun CharacterDetailScreen3(navController: NavController, characterApiId: String) {
    val viewModel = hiltViewModel<CharacterDetailViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val characterDetail by viewModel.characterDetail.collectAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.characterDetail(characterApiId)
    }

    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(DefaultBackgroundColor)
    ) {
        // Progress bar for loading state
        item { CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f) }


        // Display character details if available
        characterDetail?.let { character ->
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    CoilImage(
                        modifier = Modifier.fillMaxWidth(),
                        imageModel = { character.image?.superUrl ?: "" },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            contentDescription = "Comic Character Details"
                        ),
                        component = rememberImageComponent {
                            +CircularRevealPlugin(duration = CircularRevealPluginDuration)
                            +ShimmerPlugin(
                                shimmer = Shimmer.Flash(
                                    baseColor = SecondaryFontColor,
                                    highlightColor = DefaultBackgroundColor
                                )
                            )
                        },
                    )
                    IconButton(
                        onClick = {},
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

            item {
                // Character details content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    AnnotatedHeaderContent(
                        header = "Super Name: ",
                        content = character.name ?: "No Super Name",
                        modifier = Modifier.padding(top = 10.dp),
                        headerStyle = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                        ),
                        contentStyle = MaterialTheme.typography.bodyLarge
                    )

                    AnnotatedHeaderContent(
                        header = "Real Name: ",
                        content = character.realName ?: "No Real Name",
                        modifier = Modifier.padding(top = 10.dp),
                        headerStyle = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                        ),
                        contentStyle = MaterialTheme.typography.bodyLarge
                    )

                    character.getAliasesAsList()?.let { aliasList ->
                        AliasesSection(aliasList)
                    }

                    character.creators?.let {
                        CreatorsSection(it)
                    }

                    AnnotatedHeaderContent(
                        header = "First Appearance: ",
                        content = character.firstAppearedInIssue?.name ?: "Unavailable Info",
                        modifier = Modifier.padding(top = 10.dp),
                        headerStyle = MaterialTheme.typography.titleMedium,
                        contentStyle = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = stringResource(R.string.description),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Html(text = character.description ?: "No description available")
                }
            }

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultBackgroundColor)
    ) {
        if (showButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Scroll to top",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun CharacterDetailsScreen(navController: NavController, characterApiId: String) {
    val viewModel = hiltViewModel<CharacterDetailViewModel>()
    val isLoading by viewModel.isLoading.collectAsState()
    val characterDetail by viewModel.characterDetail.collectAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.characterDetail(characterApiId)
    }

    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultBackgroundColor)
    ) {
        LazyColumn(
            state = lazyListState, modifier = Modifier.fillMaxSize()
        ) {
            item {
                CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f)
            }

            characterDetail?.let { character ->
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        CoilImage(modifier = Modifier.fillMaxWidth(),
                            imageModel = { character.image?.superUrl ?: "" },
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
                                        highlightColor = DefaultBackgroundColor
                                    )
                                )
                            })
                        IconButton(
                            onClick = {},
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

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        AnnotatedHeaderContent(
                            header = "Super Name:\t",
                            content = character.name ?: "No Super Name",
                            modifier = Modifier.padding(top = 10.dp),
                            headerStyle = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            contentStyle = MaterialTheme.typography.bodyLarge
                        )

                        AnnotatedHeaderContent(
                            header = "Real Name: ",
                            content = character.realName ?: "No Real Name",
                            modifier = Modifier.padding(top = 10.dp),
                            headerStyle = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            contentStyle = MaterialTheme.typography.bodyLarge
                        )

                        character.getAliasesAsList()?.let { aliasList ->
                            AliasesSection(aliasList)
                        }

                        character.creators?.let {
                            CreatorsSection(it)
                        }

                        AnnotatedHeaderContent(
                            header = "First Appearance: ",
                            content = character.firstAppearedInIssue?.name ?: "Unavailable Info",
                            modifier = Modifier.padding(top = 10.dp),
                            headerStyle = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            contentStyle = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = stringResource(R.string.description),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Html(text = character.description ?: "No description available")
                    }
                }
            }
        }

        if (showButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    //.cornerRadius(30)
                    //.clip(CircleShape)
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = FloatingActionBackground
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Scroll to top",
                    tint = Color.White,
                )
            }
        }
    }
}

@Composable
fun AnnotatedHeaderContent(
    header: String,
    content: String,
    headerStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    ),
    contentStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onSurface
    ),
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = headerStyle.toSpanStyle()
        ) {
            append(header)
        }
        withStyle(
            style = contentStyle.toSpanStyle()
        ) {
            append(content)
        }
    }

    Text(
        text = annotatedString,
        modifier = modifier
            .padding(bottom = 8.dp)
            .wrapContentSize(Alignment.Center),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Html(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS))
        }
    })
}

@Composable
fun AliasesSection(aliases: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
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
fun CreatorsSection(creators: List<ComicCreator>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.creators) + ":",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
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
                    text = creator.name,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun PowersSection(powers: List<Power>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.powers) + ":",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
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
                    text = power.name,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun DescriptionSection(description: String?) {
    // State to manage dialog visibility
    var isDialogOpen by remember { mutableStateOf(false) }

    Column {
        // Button to open the dialog
        TextButton(
            onClick = { isDialogOpen = true }, colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = stringResource(R.string.description),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Dialog for displaying the description
        if (isDialogOpen) {
            AlertDialog(onDismissRequest = { isDialogOpen = false }, title = {
                Text(
                    text = stringResource(R.string.description),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }, text = {
                // Add scrollable content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 300.dp) // Limit height
                        .verticalScroll(rememberScrollState())
                ) {
                    if (!description.isNullOrEmpty()) {
                        HtmlText(description) // Custom composable to render HTML
                    } else {
                        Text("No description available.")
                    }
                }
            }, confirmButton = {
                TextButton(onClick = { isDialogOpen = false }) {
                    Text("Close")
                }
            })
        }
    }
}


@Composable
fun HtmlText(htmlText: String) {
    // Render HTML content (requires androidx.compose.ui.text.Html support)
    val annotatedText = remember(htmlText) {
        HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS).toString()
    }
    Text(text = annotatedText, style = MaterialTheme.typography.bodyMedium)
}

@Preview(name = "CharacterDetails", showBackground = true)
@Composable
fun Preview() {
}