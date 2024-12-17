package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import s.m.mota.comicvineandroidnativeapp.data.model.toComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: CharactersRepository) : ViewModel() {
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
    val allCharacters = repository.allCharacters().cachedIn(viewModelScope)

    val allCharactersUi = repository.allCharacters().map { it.map { it.toComicCharacterUi() } }.cachedIn(viewModelScope)
}