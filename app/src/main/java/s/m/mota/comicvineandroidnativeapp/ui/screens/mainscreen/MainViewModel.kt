package s.m.mota.comicvineandroidnativeapp.ui.screens.mainscreen

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.search_results.SearchResultModelRepository
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepository: SearchResultModelRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val searchResultData: MutableState<DataState<List<SearchResultModel>>?> = mutableStateOf(null)

    private val _sortSettings = MutableStateFlow<Pair<String, String>?>(null)
    val sortSettings: StateFlow<Pair<String, String>?> get() = _sortSettings.asStateFlow()

    init {
        loadSortSettings()
    }

    fun updateSortSettings(component: String, order: String) {
        _sortSettings.value = component to order

        sharedPreferences.edit().apply {
            putString("sort_component", component)
            putString("sort_order", order)
            apply()
        }
    }

    private fun loadSortSettings() {
        val component = sharedPreferences.getString("sort_component", "id")
        val order = sharedPreferences.getString("sort_order", "asc")

        if (order != null && component != null) {
            _sortSettings.value = component to order
        }
    }

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