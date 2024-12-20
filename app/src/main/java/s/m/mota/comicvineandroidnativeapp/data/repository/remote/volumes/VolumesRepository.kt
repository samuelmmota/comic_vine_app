package s.m.mota.comicvineandroidnativeapp.data.repository.remote.volumes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.ApiService
import s.m.mota.comicvineandroidnativeapp.data.datasource.remote.paging_datasource.VolumesPagingDataSource
import s.m.mota.comicvineandroidnativeapp.data.model.ComicVolume
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.utils.PAGING_CONFIG_PAGE_SIZE
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState
import javax.inject.Inject

class VolumesRepository @Inject constructor(
    private val apiService: ApiService
) : VolumesRepositoryInterface {

    override fun allVolumes(): Flow<PagingData<ComicVolume>> = Pager(
        pagingSourceFactory = { VolumesPagingDataSource(apiService) },
        config = PagingConfig(pageSize = PAGING_CONFIG_PAGE_SIZE)
    ).flow

    override suspend fun volumeDetails(volumeApiId: String): Flow<DataState<ComicVolume>> = flow {
        emit(DataState.Loading)
        try {
            val volumeResult: ComicVineApiResponse<ComicVolume> =
                apiService.getVolumeDetails(volumeApiId)
            if (volumeResult.results != null) {
                emit(DataState.Success(volumeResult.results))
            }
            //TODO: Deal with null

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}