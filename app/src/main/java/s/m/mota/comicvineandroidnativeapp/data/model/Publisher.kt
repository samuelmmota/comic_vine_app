package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType
import s.m.mota.comicvineandroidnativeapp.utils.WEBVIEW_COMIC_VINE_URL

data class Publisher(
    @SerializedName("api_detail_url") val apiDetailUrl: String?,
    @SerializedName("name") override val name: String?,
    @SerializedName("id") override val id: Int?,
    @SerializedName("site_detail_url") override val siteDetailUrl: String?,
    override val image: Image?
) : ComicResource {
    override val apiId: String? get() = apiDetailUrl?.split("/")?.dropLast(1)?.lastOrNull()
    override val resourceType get(): ComicResourceType = ComicResourceType.PUBLISHER
    override val alternativeSiteDetailUrl
        get(): String? {
            return if (!apiId.isNullOrEmpty()) {
                WEBVIEW_COMIC_VINE_URL + "${resourceType.typeName}/${apiId}"
            } else {
                null
            }
        }
}