package s.m.mota.comicvineandroidnativeapp.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class GenericPagingDataSource<T : Any> @Inject constructor(
    private val fetchResult: suspend (page: Int, pageSize: Int) -> List<T>,
    private val getNextKey: (List<T>, Int) -> Int?
) : PagingSource<Int, T>() {

    /* override fun getRefreshKey(state: PagingState<Int, T>): Int? {
         return state.anchorPosition?.let { position ->
             state.closestPageToPosition(position)?.nextKey?.minus(1)
                 ?: state.closestPageToPosition(position)?.prevKey?.plus(1)
         }
     }*/

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val result = fetchResult(page, pageSize)
            LoadResult.Page(
                data = result,
                prevKey = if (page == 1) null else page - 1,
                nextKey = getNextKey(result, page)
            )
        } catch (e: IOException) {
            Timber.e("IOException: ${e.message}")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Timber.e("HttpException: ${e.message}")
            LoadResult.Error(e)
        }
    }
}