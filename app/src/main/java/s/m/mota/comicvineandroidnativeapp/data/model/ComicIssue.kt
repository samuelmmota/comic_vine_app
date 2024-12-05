package s.m.mota.comicvineandroidnativeapp.data.model

import com.google.gson.annotations.SerializedName
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter

data class ComicIssue(
    @SerializedName("aliases") val aliases: String?, // List of aliases separated by \n
    @SerializedName("api_detail_url") val apiDetailUrl: String?, // URL to the issue detail
    @SerializedName("character_credits") val characterCredits: List<ComicCharacter>?, // Characters in the issue
    @SerializedName("characters_died_in") val charactersDiedIn: List<ComicCharacter>?, // Characters who died in the issue
    //@SerializedName("concept_credits") val conceptCredits: List<Concept>?, // Concepts in the issue
    @SerializedName("cover_date") val coverDate: String?, // Publish date on cover
    @SerializedName("date_added") val dateAdded: String?, // Date added to Comic Vine
    @SerializedName("date_last_updated") val dateLastUpdated: String?, // Last update date on Comic Vine
    @SerializedName("deck") val deck: String?, // Brief summary
    @SerializedName("description") val description: String?, // Detailed description
    //@SerializedName("disbanded_teams") val disbandedTeams: List<Team>?, // Teams disbanded in the issue
    @SerializedName("first_appearance_characters") val firstAppearanceCharacters: List<ComicCharacter>?, // Characters appearing for the first time
    //@SerializedName("first_appearance_concepts") val firstAppearanceConcepts: List<Concept>?, // Concepts appearing for the first time
    //@SerializedName("first_appearance_locations") val firstAppearanceLocations: List<Location>?, // Locations appearing for the first time
    //@SerializedName("first_appearance_objects") val firstAppearanceObjects: List<Object>?, // Objects appearing for the first time
    //@SerializedName("first_appearance_storyarcs") val firstAppearanceStoryArcs: List<StoryArc>?, // Story arcs appearing for the first time
    //@SerializedName("first_appearance_teams") val firstAppearanceTeams: List<Team>?, // Teams appearing for the first time
    @SerializedName("has_staff_review") val hasStaffReview: Boolean?, // Indicates if reviewed by staff
    @SerializedName("id") val id: Int?, // Unique ID
    @SerializedName("image") val image: Image?, // Main image of the issue
    @SerializedName("issue_number") val issueNumber: String?, // Number assigned to the issue
    //@SerializedName("location_credits") val locationCredits: List<Location>?, // Locations in the issue
    @SerializedName("name") val name: String?, // Name of the issue
    //@SerializedName("object_credits") val objectCredits: List<Object>?, // Objects in the issue
    //@SerializedName("person_credits") val personCredits: List<Person>?, // People who worked on the issue
    @SerializedName("site_detail_url") val siteDetailUrl: String?, // URL to the issue on Giant Bomb
    @SerializedName("store_date") val storeDate: String?, // Store release date
    //@SerializedName("story_arc_credits") val storyArcCredits: List<StoryArc>?, // Story arcs in the issue
    //@SerializedName("team_credits") val teamCredits: List<Team>?, // Teams in the issue
    //@SerializedName("teams_disbanded_in") val teamsDisbandedIn: List<Team>?, // Teams disbanded in the issue
    //@SerializedName("volume") val volume: Volume? // Volume this issue belongs to
)