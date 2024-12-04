package s.m.mota.comicvineandroidnativeapp.data.model.character

import com.google.gson.annotations.SerializedName

data class Power(
    @SerializedName("id") val id: Int,
    @SerializedName("api_detail_url") val apiDetailUrl: String,
    @SerializedName("name") val name: String
)