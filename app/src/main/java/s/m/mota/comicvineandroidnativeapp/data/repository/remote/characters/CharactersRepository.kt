package s.m.mota.comicvineandroidnativeapp.data.repository.remote.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.paging_datasource.CharactersPagingDataSource
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.utils.PAGING_CONFIG_PAGE_SIZE
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
) : CharactersRepositoryInterface {

    override fun allCharacters(): Flow<PagingData<ComicCharacter>> = Pager(
        pagingSourceFactory = { CharactersPagingDataSource(apiService) },
        config = PagingConfig(
            pageSize = PAGING_CONFIG_PAGE_SIZE)
    ).flow

    override suspend fun characterDetails(characterApiId: String): Flow<DataState<ComicCharacter>> = flow {
        emit(DataState.Loading)
        try {
            val characterResult: ComicVineApiResponse<ComicCharacter> =
                apiService.getCharacterDetail(characterApiId)
            if (characterResult.results != null) {
                emit(DataState.Success(characterResult.results))
            }
            //TODO: Deal with null

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}