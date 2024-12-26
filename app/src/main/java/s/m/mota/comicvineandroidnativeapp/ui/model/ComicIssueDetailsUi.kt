package s.m.mota.comicvineandroidnativeapp.ui.model

import s.m.mota.comicvineandroidnativeapp.ui.component.ComicResourceUi

data class ComicIssueDetailsUi(
    val id: String?,
    val name: String?,
    val aliases: List<String>?,
    val issueApiId: String?,
    val issueNumber: String?,
    val description: String?,
    val associatedImages: List<String>,
    val coverDate: String?,
    val dateAdded: String?,
    val dateLastUpdated: String?,
    val storeDate: String?,
    val deck: String?,
    val volume: ComicVolumeUi?,
    val hasStaffReview: Boolean,
    val personCredits: List<ComicResourceUi>?, //Creators
    val characterCredits: List<ComicResourceUi>?,
    val teamCredits: List<ComicResourceUi>?,
    val locationCredits: List<ComicResourceUi>?,
    val conceptCredits: List<ComicResourceUi>?,
    val objectCredits: List<ComicResourceUi>?,
    val storyArcCredits: List<ComicResourceUi>?,
    val firstAppearanceCharacters: List<ComicResourceUi>?,
    val firstAppearanceConcepts: List<ComicResourceUi>?,
    val firstAppearanceLocations: List<ComicResourceUi>?,
    val firstAppearanceStoryArcs: List<ComicResourceUi>?,
    val firstAppearanceTeams: List<ComicResourceUi>?,
    val teamsDisbandedIn: List<ComicResourceUi>?,
    val disbandedTeams: List<ComicResourceUi>?,
    val charactersDiedIn: List<ComicResourceUi>?
)
