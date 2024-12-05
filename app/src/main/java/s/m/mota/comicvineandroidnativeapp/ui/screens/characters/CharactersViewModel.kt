package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
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
}