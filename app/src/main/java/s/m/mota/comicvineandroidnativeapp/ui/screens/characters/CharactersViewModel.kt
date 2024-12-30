package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import s.m.mota.comicvineandroidnativeapp.data.model.toComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters.CharactersRepository
import s.m.mota.comicvineandroidnativeapp.utils.Utils.toSortStringFormat
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository, private val sharedPreferences: SharedPreferences
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

    /*
      private val _allCharacters = MutableStateFlow<PagingData<CharacterItem>?>(null)
      val allCharacters: StateFlow<PagingData<CharacterItem>?> = _allCharacters

      init {
          viewModelScope.launch {
              repository.allCharacters().cachedIn(viewModelScope).collect { pagingData ->
                  _allCharacters.value = pagingData
              }
          }
  */
    val allCharacters =
        repository.allCharacters(_sortSettings?.toSortStringFormat()).cachedIn(viewModelScope)

    val allCharactersUi = repository.allCharacters(_sortSettings?.toSortStringFormat())
        .map { it.map { it.toComicCharacterUi() } }.cachedIn(viewModelScope)
}