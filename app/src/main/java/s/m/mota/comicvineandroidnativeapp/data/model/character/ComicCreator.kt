package s.m.mota.comicvineandroidnativeapp.data.model.character

import com.google.gson.annotations.SerializedName

data class ComicCreator(
    @SerializedName("api_detail_url") val apiDetailUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("site_detail_url") val siteDetailUrl: String
)