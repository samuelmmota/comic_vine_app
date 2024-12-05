package s.m.mota.comicvineandroidnativeapp.data.repository.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.data.model.response.ResourceType
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

class SearchResultModelRepository @Inject constructor(
    private val apiService: ApiService
) : SearchResultModelRepositoryInterface {

    /*override fun allResults(): Flow<PagingData<SearchResultModel>> = Pager(
        pagingSourceFactory = { CharactersPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 100)
    ).flow
     */

    override suspend fun searchResults(query: String): Flow<DataState<List<SearchResultModel>>> = flow {
        emit(DataState.Loading)
        try {
            val searchResults: ComicVineApiResponse<List<SearchResultModel>> =
                apiService.getSearchResults(
                    query = query,
                )
            if (searchResults.results != null) {
                emit(DataState.Success(searchResults.results))
            }
            //TODO: Deal with null

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun searchResultsByType(query: String, types: List<ResourceType>): Flow<DataState<List<SearchResultModel>>> = flow {
        emit(DataState.Loading)
        try {
            val searchResults: ComicVineApiResponse<List<SearchResultModel>> =
                apiService.getSearchResults(
                    query = query, resources = types.joinToString { it.typeName +"," }
                )
            if (searchResults.results != null) {
                emit(DataState.Success(searchResults.results))
            }
            //TODO: Deal with null

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}