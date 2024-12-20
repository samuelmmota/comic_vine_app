package s.m.mota.comicvineandroidnativeapp.data.repository.remote.issues

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState

interface IssuesRepositoryInterface {
    fun allIssues(): Flow<PagingData<ComicIssue>>
    suspend fun issueDetails(issueApiId: String): Flow<DataState<ComicIssue>>
}