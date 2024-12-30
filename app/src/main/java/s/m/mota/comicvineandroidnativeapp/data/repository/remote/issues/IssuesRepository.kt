package s.m.mota.comicvineandroidnativeapp.data.repository.remote.issues

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.paging_datasource.IssuesPagingDataSource
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.utils.PAGING_CONFIG_PAGE_SIZE
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

class IssuesRepository @Inject constructor(
    private val apiService: ApiService
) : IssuesRepositoryInterface {

    override fun allIssues(sort: String?): Flow<PagingData<ComicIssue>> = Pager(
        pagingSourceFactory = { IssuesPagingDataSource(apiService = apiService, sort = sort)},
        config = PagingConfig(pageSize = PAGING_CONFIG_PAGE_SIZE)
    ).flow

    override suspend fun issueDetails(issueApiId: String): Flow<DataState<ComicIssue>> = flow {
        emit(DataState.Loading)
        try {
            val issueResult: ComicVineApiResponse<ComicIssue> =
                apiService.getIssueDetails(issueApiId)
            if (issueResult.results != null) {
                emit(DataState.Success(issueResult.results))
            }
            //TODO: Deal with null

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}