package s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState

interface CharactersRepositoryInterface {
    fun allCharacters(): Flow<PagingData<ComicCharacter>>
    suspend fun characterDetail(characterApiId: String): Flow<DataState<ComicCharacter>>
}