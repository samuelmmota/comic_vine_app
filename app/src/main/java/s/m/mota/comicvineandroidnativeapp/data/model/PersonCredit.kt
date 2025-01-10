package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType

data class PersonCredit(
    @SerializedName("id") override val id: Int?,
    @SerializedName("name") override val name: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("count") val issueCount: String?,
    @SerializedName("api_detail_url") val apiDetailUrl: String?, // URL to the issue detail
    @SerializedName("site_detail_url") override val siteDetailUrl: String?,
    override val image: Image?,
) : ComicResource {
    override val apiId get(): String? = apiDetailUrl?.split("/")?.dropLast(1)?.lastOrNull()
    override val resourceType get() : ComicResourceType = ComicResourceType.PERSON
}
