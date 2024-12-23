package s.m.mota.comicvineandroidnativeapp.data.repository.remote.search_results

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.utils.SEARCH_RESULT_FETCH_LIMIT
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
                    limit = SEARCH_RESULT_FETCH_LIMIT
                )
            if (searchResults.results != null) {
                emit(DataState.Success(searchResults.results))
            }
            //TODO: Deal with null

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun searchResultsByType(query: String, types: List<ComicResourceType>): Flow<DataState<List<SearchResultModel>>> = flow {
        emit(DataState.Loading)
        try {
            val searchResults: ComicVineApiResponse<List<SearchResultModel>> =
                apiService.getSearchResults(
                    query = query, resources = types.joinToString { it.typeName +"," },
                    limit = SEARCH_RESULT_FETCH_LIMIT
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