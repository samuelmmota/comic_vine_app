package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.character.CharacterCredit

data class ComicVolume(
    @SerializedName("aliases") val aliases: String?,
    @SerializedName("api_detail_url") val apiDetailUrl: String?,
    @SerializedName("character_credits") val characterCredits: List<CharacterCredit>?,
    @SerializedName("concept_credits") val conceptCredits: List<ConceptCredit>?,
    @SerializedName("count_of_issues") val countOfIssues: Int?,
    @SerializedName("date_added") val dateAdded: String?,
    @SerializedName("date_last_updated") val dateLastUpdated: String?,
    @SerializedName("deck") val deck: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("first_issue") val firstIssue: ComicIssue?,
    @SerializedName("id") val id: Int?,
    @SerializedName("image") val image: Image?,
    @SerializedName("last_issue") val lastIssue: ComicIssue?,
    @SerializedName("location_credits") val locationCredits: List<LocationCredit>?,
    @SerializedName("name") val name: String?,
    @SerializedName("object_credits") val objectCredits: List<ObjectCredit>?,
    @SerializedName("person_credits") val personCredits: List<PersonCredit>?,
    @SerializedName("publisher") val publisher: Publisher?,
    @SerializedName("site_detail_url") val siteDetailUrl: String?,
    @SerializedName("start_year") val startYear: String?,
    @SerializedName("team_credits") val teamCredits: List<TeamCredit>?
)