package s.m.mota.comicvineandroidnativeapp.data.repository.remote

import kotlinx.coroutines.flow.Flow
import s.m.mota.comicvineandroidnativeapp.data.model.response.ResourceType
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState

interface SearchResultModelRepositoryInterface {
    //fun allResults(): Flow<PagingData<List<SearchResultModel>>>>
    suspend fun searchResults(query: String): Flow<DataState<List<SearchResultModel>>>
    suspend fun searchResultsByType(query: String, types: List<ResourceType>): Flow<DataState<List<SearchResultModel>>>
}