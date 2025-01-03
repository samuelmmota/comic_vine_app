package s.m.mota.comicvineandroidnativeapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import s.m.mota.comicvineandroidnativeapp.R
import s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen.MainViewModel
import s.m.mota.comicvineandroidnativeapp.utils.Utils.uiToJsonOrderMap
import s.m.mota.comicvineandroidnativeapp.utils.Utils.uiToJsonSortMap

@Composable
fun SortSettingAlertDialog(
    viewmodel: MainViewModel,
    cancel: (isOpen: Boolean) -> Unit,
    apply: (isOpen: Boolean) -> Unit,
) {
    val openDialog = remember { mutableStateOf(true) }

    val componentsSortMap = mapOf(
        1 to stringResource(R.string.sort_id),
        2 to stringResource(R.string.sort_added_date),
        3 to stringResource(R.string.sort_updated_date)
    )

    val componentsOrderMap = mapOf(
        1 to stringResource(R.string.sort_ascending),
        2 to stringResource(R.string.sort_descending),
    )


    var selectedComponentId by remember {
        mutableIntStateOf(viewmodel.sortSettings.value?.first?.let { jsonValue ->
            uiToJsonSortMap.entries.firstOrNull { it.value == jsonValue }?.key
        } ?: uiToJsonSortMap.keys.first())
    }

    var selectedSortOrderId by remember {
        mutableIntStateOf(viewmodel.sortSettings.value?.second?.let { jsonValue ->
            uiToJsonOrderMap.entries.firstOrNull { it.value == jsonValue }?.key
        } ?: uiToJsonOrderMap.keys.first())
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                cancel(false)
            },
            title = {
                Text(
                    text = "Sort By", fontWeight = FontWeight.Bold, fontSize = 18.sp
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    var expanded by remember { mutableStateOf(false) }
                    Box {
                        TextButton(
                            onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = componentsSortMap.get(selectedComponentId) ?: "id",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            componentsSortMap.forEach { component ->
                                DropdownMenuItem(onClick = {
                                    selectedComponentId = component.key
                                    expanded = false
                                }, text = {
                                    Text(
                                        text = component.value,
                                        fontSize = 14.sp,
                                        color = if (component.key == selectedComponentId) Color.Blue else Color.Black
                                    )
                                })
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        componentsOrderMap.forEach { componentOrder ->
                            Row(modifier = Modifier
                                .clickable {
                                    selectedSortOrderId = componentOrder.key
                                }
                                .padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "${componentOrder.value} Order",
                                    tint = if (selectedSortOrderId == componentOrder.key) Color.Blue else Color.Gray,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = componentOrder.value,
                                    fontSize = 16.sp,
                                    color = if (selectedSortOrderId == componentOrder.key) Color.Blue else Color.Gray,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    viewmodel.updateSortSettings(
                        uiToJsonSortMap[selectedComponentId] ?: "id",
                        uiToJsonOrderMap[selectedSortOrderId] ?: "desc"
                    )
                    apply(false)
                }) {
                    Text(
                        text = "Apply", fontWeight = FontWeight.Bold, color = Color.Black
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    cancel(false)
                }) {
                    Text(
                        text = "Cancel", fontWeight = FontWeight.Bold, color = Color.Black
                    )
                }
            },
        )
    }
}