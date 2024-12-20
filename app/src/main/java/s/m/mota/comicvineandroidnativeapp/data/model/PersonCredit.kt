package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName

data class PersonCredit(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("role") val role: String?
)
