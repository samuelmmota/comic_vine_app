package s.m.mota.comicvineandroidnativeapp.data.datasource.remote.paging_datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter

import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class CharactersPagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val limit: Int = 10,
    private val sort: String? = null,
    private val filter: String? = null
) : PagingSource<Int, ComicCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, ComicCharacter>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicCharacter> {
        // Ex result: "limit":100,"offset":0,"number_of_page_results":100,"number_of_total_results":163652,
        return try {
            val nextPage = params.key ?: 0
            println("offset number: $nextPage")
            val charactersList = apiService.getCharacters(
                offset = nextPage, limit = limit, sort = sort, filter = filter
            )

            val nextKey = if (charactersList.numberOfPageResults!! > 0 &&
                nextPage + charactersList.numberOfPageResults!! < charactersList.numberOfTotalResults!!
            ) {
                nextPage + 1
            } else {
                null
            }
            val prevKey = if (nextPage > 0) nextPage - 1 else null

            LoadResult.Page(
                data = charactersList.results ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
                //prevKey = if (nextPage == 1) null else nextPage - 1,
                //nextKey = if (charactersList.results!!.isNotEmpty()) charactersList.offset!! + 1 else null
                //nextKey = if (charactersList.results!!.isNotEmpty()) nextPage + limit else null //Only for Testing with a mock API
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