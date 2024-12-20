package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.character.CharacterCredit
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue

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
) {
    val aliasesList: List<String>?
        get() {
            if (aliases.isNullOrEmpty()) return null
            return aliases.split("\n")
        }

    val aliasesAsString: String? get() = aliasesList?.joinToString(", ")

    val volumeApiId: String? get() = apiDetailUrl?.split("/")?.dropLast(1)?.lastOrNull()

    val characterCreditsList: List<String>?
        get() {
            if (characterCredits.isNullOrEmpty()) return null
            return characterCredits.mapNotNull { it.name }
        }

    val locationCreditsList: List<String>?
        get() {
            if (locationCredits.isNullOrEmpty()) return null
            return locationCredits.mapNotNull { it.name }
        }

    val objectCreditsList: List<String>?
        get() {
            if (objectCredits.isNullOrEmpty()) return null
            return objectCredits.mapNotNull { it.name }
        }
    val personCreditsList: List<String>?
        get() {
            if (personCredits.isNullOrEmpty()) return null
            return personCredits.mapNotNull { it.name }
        }
    val teamCreditsList: List<String>?
        get() {
            if (teamCredits.isNullOrEmpty()) return null
            return teamCredits.mapNotNull { it.name }
        }
}