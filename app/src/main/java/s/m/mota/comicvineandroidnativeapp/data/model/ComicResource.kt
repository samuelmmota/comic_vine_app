package s.m.mota.comicvineandroidnativeapp.data.model

import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType

interface ComicResource {
    val id: Int?
    val name: String?
    val image: Image?
    val apiId: String?
    val resourceType: ComicResourceType
}