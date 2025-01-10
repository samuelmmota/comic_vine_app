package s.m.mota.comicvineandroidnativeapp.data.model.response

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.Image
import s.m.mota.comicvineandroidnativeapp.data.model.Publisher

data class SearchResultModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: Image?,
    @SerializedName("api_detail_url") val apiDetailUrl: String?,
    @SerializedName("count_of_issue_appearances") val countOfIssueAppearances: Int?,
    @SerializedName("count_of_issues") val countOfIssues: Int?,
    @SerializedName("count_of_episodes") val countOfEpisodes: Int?,
    @SerializedName("start_year") val startYear: Int?,
    @SerializedName("publisher") val publisher: Publisher?,
    @SerializedName("resource_type") val resourceType: String?
) {
    fun getResourceType(): ComicResourceType? {
        return if (resourceType.isNullOrEmpty()) {
            return null
        } else {
            ComicResourceType.fromTypeName(
                resourceType
            )
        }
    }

    fun convertInDataModel(): Any? {
        return when (getResourceType()) {
            //ResourceType.CHARACTER -> toComicCharacterModel()
            else -> null
        }
    }

    val resourceApiId
        get():String? {
            val apiDetailUrl = apiDetailUrl ?: return null
            val parts = apiDetailUrl.split("/").dropLast(1)
            return parts.lastOrNull()
        }
}

enum class ComicResourceType(val typeName: String) {
    CHARACTER("character"), CONCEPT("concept"), ORIGIN("origin"), OBJECT("object"), LOCATION("location"), ISSUE(
        "issue"
    ),
    STORY_ARC("story_arc"), VOLUME("volume"), PUBLISHER("publisher"), PERSON("person"), TEAM("team"), VIDEO(
        "video"
    ),
    POWER("power"),
    SERIES(
        "series"
    );

    companion object {
        fun fromTypeName(typeName: String): ComicResourceType? {
            return entries.find { it.typeName == typeName }
        }
    }
}

// ExtensionFunction
/*
fun SearchResultModel.toComicCharacterModel(): ComicCharacter {
    return ComicCharacter(
        id = this.id,
        name = this.name,
        countOfIssueAppearances = this.countOfIssueAppearances,
        publisher = this.publisher,
        apiDetailUrl = this.apiDetailUrl
    )
}*/