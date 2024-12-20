package s.m.mota.comicvineandroidnativeapp.data.model.character

import com.google.gson.annotations.SerializedName

data class CharacterCredit(
    @SerializedName("id") val id: Int?, @SerializedName("name") val name: String?
)
