package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import s.m.mota.comicvineandroidnativeapp.data.model.toComicVolumeUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.volumes.VolumesRepository
import s.m.mota.comicvineandroidnativeapp.utils.Utils.toSortStringFormat
import javax.inject.Inject

@HiltViewModel
class VolumeViewModel @Inject constructor(
    private val repository: VolumesRepository, private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _sortSettings: Pair<String, String>?
        get() {
            val component = sharedPreferences.getString("sort_component", "id")
            val order = sharedPreferences.getString("sort_order", "asc")

            return if (order != null && component != null) {
                component to order
            } else {
                null
            }
        }
    val allVolumes =
        repository.allVolumes(_sortSettings?.toSortStringFormat()).cachedIn(viewModelScope)
    val allVolumesUi = repository.allVolumes(_sortSettings?.toSortStringFormat())
        .map { it.map { it.toComicVolumeUi() } }.cachedIn(viewModelScope)
}