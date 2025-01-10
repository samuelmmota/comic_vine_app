package s.m.mota.comicvineandroidnativeapp.ui.model

data class ComicVolumeDetailsUi(
    val id: String?,
    val name: String?,
    val volumeApiId: String?,
    val imageUrl: String?,
    val countOfIssues: String?,
    val dateAdded: String?,
    val dateLastUpdated: String?,
    val firstIssue: ComicIssueUi?,
    val lastIssue: ComicIssueUi?,
    val publisher: String?,
    val startYear: String?,
    val deck: String?,
    val description: String?,
    val locationCreditsUi:  List<ComicResourceUi>?,
    val characterCreditsUi: List<ComicResourceUi>?,
    val objectCreditsUi:  List<ComicResourceUi>?,
    val personCreditsUi: List<ComicResourceUi>?,
    val teamCreditsUi:  List<ComicResourceUi>?,
    val siteDetailUrl: String?
)