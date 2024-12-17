package s.m.mota.comicvineandroidnativeapp.data.model.character

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.ComicIssue
import s.m.mota.comicvineandroidnativeapp.data.model.Image

data class ComicCharacter(
    @SerializedName("aliases") val aliases: String?,
    @SerializedName("api_detail_url") val apiDetailUrl: String?,
    @SerializedName("birth") val birth: String?,
    @SerializedName("count_of_issue_appearances") val countOfIssueAppearances: Int?,
    @SerializedName("date_added") val dateAdded: String?,
    @SerializedName("date_last_updated") val dateLastUpdated: String?,
    @SerializedName("deck") val deck: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("first_appeared_in_issue") val firstAppearedInIssue: ComicIssue?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("image") val image: Image?,
    @SerializedName("name") val name: String?,
    @SerializedName("origin") val origin: Origin?,
    @SerializedName("publisher") val publisher: Publisher?,
    @SerializedName("real_name") val realName: String?,
    @SerializedName("site_detail_url") val siteDetailUrl: String?,
    @SerializedName("powers") val powers: List<Power>?,
    @SerializedName("creators") val creators: List<ComicCreator>?,
    @SerializedName("issues_died_in") val issuesDiedIn: List<ComicIssue>?,

    /*@SerializedName("character_enemies") val characterEnemies: List<CharacterReference>,
    @SerializedName("character_friends") val characterFriends: List<CharacterReference>,

    @SerializedName("issue_credits") val issueCredits: List<Issue>?,

    @SerializedName("movies") val movies: List<Movie>,

    @SerializedName("story_arc_credits") val storyArcCredits: List<StoryArc>,
    @SerializedName("team_enemies") val teamEnemies: List<Team>,
    @SerializedName("team_friends") val teamFriends: List<Team>,
    @SerializedName("teams") val teams: List<Team>,
    @SerializedName("volume_credits") val volumeCredits: List<Volume>*/
) {
    val genderDescription: String
        get() = when (gender) {
            1 -> "Male"
            2 -> "Female"
            3 -> "Other"
            else -> "No summary Available"
        }


    val aliasesList: List<String>?
        get() {
            if (aliases.isNullOrEmpty()) return null
            return aliases.split("\n")
        }

    val aliasesAsString: String? get() = aliasesList?.joinToString(", ")

    val creatorsList: List<String>? get() = creators?.map { it.name }?.takeIf { it.isNotEmpty() }

    val creatorsFormated: String? get() = creatorsList?.joinToString(", ")

    val powersList: List<String>? get() = powers?.map { it.name }?.takeIf { it.isNotEmpty() }

    val powersFormatedString: String? get() = powersList?.joinToString(", ")

    val characterApiId: String? get() = apiDetailUrl?.split("/")?.dropLast(1)?.lastOrNull()
}



