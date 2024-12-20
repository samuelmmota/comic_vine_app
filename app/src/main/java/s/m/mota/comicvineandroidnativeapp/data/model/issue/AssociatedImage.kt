package s.m.mota.comicvineandroidnativeapp.data.model.issue

import com.google.gson.annotations.SerializedName

data class AssociatedImage(
    @SerializedName("original_url") val originalUrl: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("caption") val caption: String?,
    @SerializedName("image_tags") val imageTags: String?
)