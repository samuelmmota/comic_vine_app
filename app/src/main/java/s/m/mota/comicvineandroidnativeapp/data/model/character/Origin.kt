package s.m.mota.comicvineandroidnativeapp.data.model.character

import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("api_details_url") val apiDetailUrl: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
)