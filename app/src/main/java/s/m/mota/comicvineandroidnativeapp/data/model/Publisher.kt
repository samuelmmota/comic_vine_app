package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName

data class Publisher(
    @SerializedName("api_detail_url") val apiDetailUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String
)