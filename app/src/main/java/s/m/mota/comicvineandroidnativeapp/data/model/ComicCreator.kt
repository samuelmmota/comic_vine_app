package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType

data class ComicCreator(
    @SerializedName("api_detail_url") val apiDetailUrl: String?,
    @SerializedName("name") override val name: String?,
    @SerializedName("id") override val id: Int?,
    @SerializedName("site_detail_url") override val siteDetailUrl: String?,
    override val image: Image?
): ComicResource {
    override val apiId get(): String? =  apiDetailUrl?.split("/")?.dropLast(1)?.lastOrNull()
    override val resourceType get(): ComicResourceType = ComicResourceType.PERSON
}