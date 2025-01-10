package s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details

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
import s.m.mota.comicvineandroidnativeapp.data.model.toComicIssueDetailsUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.issues.IssuesRepository
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicIssueDetailsUi
import s.m.mota.comicvineandroidnativeapp.utils.ComicHtmlElement
import s.m.mota.comicvineandroidnativeapp.utils.Utils
import s.m.mota.comicvineandroidnativeapp.utils.Utils.parseHtmlAsync
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IssueDetailsViewModel @Inject constructor(
    private val repository: IssuesRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val issueApiId: String? = savedStateHandle[Screen.IssueDetailsScreen.objectName]
    private val _issueDetails = MutableStateFlow<ComicIssueDetailsUi?>(null)
    val issueDetails get() = _issueDetails.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()

    private val _parsedDescription = MutableStateFlow<List<ComicHtmlElement>?>(null)
    val parsedDescription: StateFlow<List<ComicHtmlElement>?> get() = _parsedDescription.asStateFlow()

    init {
        if (issueApiId != null) {
            getIssueDetails(issueApiId)
        }
    }

    private suspend fun parseHtml(description: String) {
        withContext(Dispatchers.IO) {
            val parsedText = parseHtmlAsync(description)
            val annotatedString = Utils.parseHtmlToHtmlElements(parsedText)
            _parsedDescription.value = annotatedString
        }
    }

    private fun getIssueDetails(issueApiId: String) {
        viewModelScope.launch {
            repository.issueDetails(issueApiId).onEach {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _issueDetails.value = it.data.toComicIssueDetailsUi()
                        _isLoading.value = false
                        _issueDetails.value?.description?.let { description ->
                            parseHtml(description) }
                    }

                    is DataState.Error -> {
                        Timber.e("issueDetails(issueId= $issueApiId); DataState.Error: ${it.exception}")
                        _isLoading.value = false
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}