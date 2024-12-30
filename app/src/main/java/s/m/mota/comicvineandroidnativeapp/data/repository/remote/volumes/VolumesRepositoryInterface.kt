package s.m.mota.comicvineandroidnativeapp.data.repository.remote.volumes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import s.m.mota.comicvineandroidnativeapp.data.model.ComicVolume
import s.m.mota.comicvineandroidnativeapp.utils.network.DataState

interface VolumesRepositoryInterface {
    fun allVolumes(sort: String?): Flow<PagingData<ComicVolume>>
    suspend fun volumeDetails(volumeApiId: String): Flow<DataState<ComicVolume>>
}