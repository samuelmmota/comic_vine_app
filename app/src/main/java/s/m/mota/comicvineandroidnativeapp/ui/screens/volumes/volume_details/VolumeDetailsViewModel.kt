package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import s.m.mota.comicvineandroidnativeapp.data.model.html.ComicHtmlAnnotatedElement
import s.m.mota.comicvineandroidnativeapp.data.model.toComicVolumeDetailsUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.volumes.VolumesRepository
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicVolumeDetailsUi
import s.m.mota.comicvineandroidnativeapp.utils.Utils
import s.m.mota.comicvineandroidnativeapp.utils.Utils.parseHtmlAsync
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VolumeDetailsViewModel @Inject constructor(
    private val repository: VolumesRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val volumeApiId: String? = savedStateHandle[Screen.VolumeDetailsScreen.objectName]
    private val _volumeDetails = MutableStateFlow<ComicVolumeDetailsUi?>(null)
    val volumeDetails get() = _volumeDetails.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()

    private val _parsedDescription = MutableStateFlow<List<ComicHtmlAnnotatedElement>?>(null)
    val parsedDescription: StateFlow<List<ComicHtmlAnnotatedElement>?> get() = _parsedDescription.asStateFlow()

    init {
        if (volumeApiId != null) {
            getVolumeDetails(volumeApiId)
        }
    }

    private suspend fun parseHtml(description: String) {
        withContext(Dispatchers.IO) {
            val parsedText = parseHtmlAsync(description)
            val annotatedString = Utils.parseHtmlToHtmlElements(parsedText)
            _parsedDescription.value = annotatedString
        }
    }

    private fun getVolumeDetails(volumeApiId: String) {
        viewModelScope.launch {
            repository.volumeDetails(volumeApiId).onEach {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _volumeDetails.value = it.data.toComicVolumeDetailsUi()
                        _isLoading.value = false
                        _volumeDetails.value?.description?.let { description ->
                            parseHtml(description)
                        }
                    }

                    is DataState.Error -> {
                        Timber.e("volumeDetails(volumeId= $volumeApiId); DataState.Error: ${it.exception}")
                        _isLoading.value = false
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}