package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters.CharactersRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharactersRepository,
) : ViewModel() {
    private val _characterDetail = MutableStateFlow<ComicCharacter?>(null)
    val characterDetail get() = _characterDetail.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()

    fun characterDetail(characterApiId: String) {
        viewModelScope.launch {
            repository.characterDetail(characterApiId).onEach {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _characterDetail.value = it.data
                        _isLoading.value = false
                    }

                    is DataState.Error -> {
                        Timber.e("characterDetail(characterId= $characterApiId); DataState.Error: ${it.exception}")
                        _isLoading.value = false
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}