package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import s.m.mota.comicvineandroidnativeapp.data.model.toComicCharacterDetailsUi
import s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters.CharactersRepository
import s.m.mota.comicvineandroidnativeapp.navigation.Screen
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: CharactersRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val characterApiId: String? = savedStateHandle[Screen.CharacterDetailsScreen.objectName]
    private val _characterDetails = MutableStateFlow<ComicCharacterDetailsUi?>(null)
    val characterDetails get() = _characterDetails.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()


    init {
        if (characterApiId != null) {
            getCharacterDetails(characterApiId)
        }
    }

    fun getCharacterDetails(characterApiId: String) {
        viewModelScope.launch {
            repository.characterDetails(characterApiId).onEach {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _characterDetails.value = it.data.toComicCharacterDetailsUi()
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