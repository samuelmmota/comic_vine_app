package s.m.mota.comicvineandroidnativeapp.data.datasource.remote.paging_datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.model.ComicIssue
import s.m.mota.comicvineandroidnativeapp.utils.LIST_RESULT_FETCH_LIMIT
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class IssuesPagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val limit: Int = LIST_RESULT_FETCH_LIMIT,
    private val sort: String? = null,
    private val filter: String? = null
) : PagingSource<Int, ComicIssue>() {

    override fun getRefreshKey(state: PagingState<Int, ComicIssue>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicIssue> {
        return try {
            val nextPage = params.key ?: 0
            val issuesList = apiService.getIssues(
                offset = nextPage, limit = limit, sort = sort, filter = filter
            )

            val nextKey =
                if (issuesList.numberOfPageResults!! > 0 && nextPage + issuesList.numberOfPageResults < issuesList.numberOfTotalResults!!) {
                    nextPage + 1
                } else {
                    null
                }
            val prevKey = if (nextPage > 0) nextPage - 1 else null

            LoadResult.Page(
                data = issuesList.results ?: emptyList(), prevKey = prevKey, nextKey = nextKey
            )


        } catch (exception: IOException) {
            Timber.e("exception ${exception.message}")
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            Timber.e("httpException ${httpException.message}")
            return LoadResult.Error(httpException)
        }
    }
}