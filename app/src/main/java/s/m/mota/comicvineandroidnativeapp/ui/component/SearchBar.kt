package s.m.mota.comicvineandroidnativeapp.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen.MainViewModel
import s.m.mota.comicvineandroidnativeapp.ui.theme.Blue

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun SearchBar(isAppBarVisible: MutableState<Boolean>, viewModel: MainViewModel,onCloseClick: ()->Unit) {
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    BackHandler(isAppBarVisible.value.not()) {
        isAppBarVisible.value = true
    }
    Column {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
            value = text,
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                disabledLabelColor = Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                text = it
                viewModel.search(it)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Close Search bar",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .offset(x = 10.dp)
                        .clickable {
                            onCloseClick.invoke()
                        })

            },
            trailingIcon = {
                if (text.trim().isNotEmpty()) {
                    Icon(Icons.Filled.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset(x = 10.dp)
                            .clickable {
                                text = ""
                            })
                } /*else {
                    Icon(Icons.Filled.Search,
                        contentDescription = "search",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset(x = 10.dp)
                            .clickable {
                                onSearchClick.invoke()
                            })
                }*/
            })
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}