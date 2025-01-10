package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicResourceType

data class ComicVolume(
    @SerializedName("aliases") val aliases: String?,
    @SerializedName("api_detail_url") val apiDetailUrl: String?,
    @SerializedName("characters") val characterCredits: List<ComicCharacter>?,
    @SerializedName("concepts") val conceptCredits: List<ConceptCredit>?,
    @SerializedName("count_of_issues") val countOfIssues: Int?,
    @SerializedName("date_added") val dateAdded: String?,
    @SerializedName("date_last_updated") val dateLastUpdated: String?,
    @SerializedName("deck") val deck: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("first_issue") val firstIssue: ComicIssue?,
    @SerializedName("id") override val id: Int?,
    @SerializedName("image") override val image: Image?,
    @SerializedName("last_issue") val lastIssue: ComicIssue?,
    @SerializedName("locations") val locationCredits: List<LocationCredit>?,
    @SerializedName("name") override val name: String?,
    @SerializedName("objects") val objectCredits: List<ObjectCredit>?,
    @SerializedName("people") val personCredits: List<PersonCredit>?,
    @SerializedName("publisher") val publisher: Publisher?,
    @SerializedName("issues") val issues: List<ComicIssue>?,
    @SerializedName("site_detail_url") val siteDetailUrl: String?,
    @SerializedName("start_year") val startYear: String?,
    @SerializedName("teams") val teamCredits: List<TeamCredit>?
) : ComicResource {
    override val apiId: String? get() = volumeApiId
    override val resourceType get(): ComicResourceType = ComicResourceType.VOLUME

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