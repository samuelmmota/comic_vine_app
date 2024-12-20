package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import s.m.mota.comicvineandroidnativeapp.data.model.toComicVolumeUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.volumes.VolumesRepository
import javax.inject.Inject

@HiltViewModel
class VolumeViewModel @Inject constructor(private val repository: VolumesRepository) : ViewModel() {
    val allVolumes = repository.allVolumes().cachedIn(viewModelScope)
    val allVolumesUi =
        repository.allVolumes().map { it.map { it.toComicVolumeUi() } }.cachedIn(viewModelScope)
}