package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchOrderSetting
import s.m.mota.comicvineandroidnativeapp.data.model.response.FetchSortSetting
import s.m.mota.comicvineandroidnativeapp.data.model.toComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters.CharactersRepository
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.utils.Utils.toSortStringFormat
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository, private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _sortOrderSettings = MutableStateFlow(getSharedPreferencesSortSettings)
    val sortOrderSettings: StateFlow<Pair<FetchSortSetting, FetchOrderSetting>> = _sortOrderSettings

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == "sort_component" || key == "sort_order") {
                _sortOrderSettings.value = getSharedPreferencesSortSettings
            }
        }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    private val getSharedPreferencesSortSettings
        get(): Pair<FetchSortSetting, FetchOrderSetting> {
            val component =
                sharedPreferences.getString("sort_component", FetchSortSetting.DEFAULT.jsonName)
            val order =
                sharedPreferences.getString("sort_order", FetchOrderSetting.DEFAULT.jsonName)

            val sortSetting = component?.let { FetchSortSetting.fromJsonName(it) }
            val orderSetting = order?.let { FetchOrderSetting.fromJsonName(it) }

            return if (sortSetting != null && orderSetting != null) {
                sortSetting to orderSetting
            } else {
                FetchSortSetting.DEFAULT to FetchOrderSetting.DEFAULT
            }
        }

    override fun onCleared() {
        super.onCleared()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    /*
      private val _allCharacters = MutableStateFlow<PagingData<CharacterItem>?>(null)
      val allCharacters: StateFlow<PagingData<CharacterItem>?> = _allCharacters

      init {
          viewModelScope.launch {
              repository.allCharacters().cachedIn(viewModelScope).collect { pagingData ->
                  _allCharacters.value = pagingData
              }
          }

    val allCharacters =
        repository.allCharacters(_sortSettings?.toSortStringFormat()).cachedIn(viewModelScope)

    val allCharactersUi = repository.allCharacters(_sortSettings?.toSortStringFormat())
        .map { it.map { it.toComicCharacterUi() } }.cachedIn(viewModelScope)
*/

    @OptIn(ExperimentalCoroutinesApi::class)
    val allCharactersUi = _sortOrderSettings.flatMapLatest { sortSettings ->
        flowOf(PagingData.empty<ComicCharacterUi>())
        repository.allCharacters(sortSettings.toSortStringFormat()).map { pagingData ->
            pagingData.map { character -> character.toComicCharacterUi() }
        }
    }.cachedIn(viewModelScope)
}