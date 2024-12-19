package s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details

import androidx.compose.ui.text.AnnotatedString
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

    private val _parsedDescription = MutableStateFlow<AnnotatedString?>(null)
    val parsedDescription: StateFlow<AnnotatedString?> get() = _parsedDescription.asStateFlow()

    init {
        if (issueApiId != null) {
            getIssueDetails(issueApiId)
        }
    }

    private suspend fun parseHtml(description: String) {
        withContext(Dispatchers.IO) {
            val parsedText = parseHtmlAsync(description)
            val anotatedString = Utils.parseHtmlToAnnotatedString(parsedText)
            _parsedDescription.value = anotatedString
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
                        parseHtml(_issueDetails.value!!.description)
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