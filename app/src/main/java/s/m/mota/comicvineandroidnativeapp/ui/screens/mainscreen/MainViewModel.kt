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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchOrderSetting
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchSortSetting
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.data.model.shared_preferences.SharedPreferencesKeysEnum
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.search_results.SearchResultModelRepository
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepository: SearchResultModelRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val searchResultData: MutableState<DataState<List<SearchResultModel>>?> = mutableStateOf(null)

    private val _sortOrderSettings = MutableStateFlow(getSharedPreferencesSortSettings)
    val sortOrderSettings: StateFlow<Pair<FetchSortSetting, FetchOrderSetting>> = _sortOrderSettings

    fun updateSortSettings(sortSetting: FetchSortSetting, orderSetting: FetchOrderSetting) {
        _sortOrderSettings.value = sortSetting to orderSetting

        sharedPreferences.edit().apply {
            putString(SharedPreferencesKeysEnum.SORT_COMPONENT.nameKey, sortSetting.jsonName)
            putString(SharedPreferencesKeysEnum.SORT_ORDER.nameKey, orderSetting.jsonName)
            apply()
        }
    }

    private val getSharedPreferencesSortSettings
        get(): Pair<FetchSortSetting, FetchOrderSetting> {
            val component =
                sharedPreferences.getString(SharedPreferencesKeysEnum.SORT_COMPONENT.nameKey, FetchSortSetting.DEFAULT.jsonName)
            val order =
                sharedPreferences.getString(SharedPreferencesKeysEnum.SORT_ORDER.nameKey, FetchOrderSetting.DEFAULT.jsonName)

            val sortSetting = component?.let { FetchSortSetting.fromJsonName(it) }
            val orderSetting = order?.let { FetchOrderSetting.fromJsonName(it) }

            return if (sortSetting != null && orderSetting != null) {
                sortSetting to orderSetting
            } else {
                FetchSortSetting.DEFAULT to FetchOrderSetting.DEFAULT
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