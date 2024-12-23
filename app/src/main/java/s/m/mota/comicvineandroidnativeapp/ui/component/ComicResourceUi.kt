package s.m.mota.comicvineandroidnativeapp.ui.component

import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType

data class ComicResourceUi(
    val id: Int?,
    val name: String?,
    val thumbnailImageUrl: String?,
    val apiId: String?,
    val resourceType: ComicResourceType?,
)