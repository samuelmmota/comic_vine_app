package s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.SearchBaseModelRepository
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchRepository: SearchBaseModelRepository) :
    ViewModel() {
    val searchResultData: MutableState<DataState<List<SearchResultModel>>?> = mutableStateOf(null)

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun search(searchQuery: String) {
        viewModelScope.launch {
            flowOf(searchQuery).debounce(300).filter {
                    it.trim().isEmpty().not()
                }.distinctUntilChanged().flatMapLatest {
                    searchRepository.searchResults(it)
                }.collect {
                    if (it is DataState.Success) {
                        it.data
                    }
                    searchResultData.value = it
                }
        }
    }
}