package s.m.mota.comicvineandroidnativeapp.ui.model

data class ComicVolumeDetailsUi(
    val id: String?,
    val name: String?,
    val volumeApiId: String?,
    val imageUrl: String,
    val countOfIssues: String?,
    val dateAdded: String?,
    val dateLastUpdated: String?,
    val firstIssue: ComicIssueUi?,
    val lastIssue: ComicIssueUi?,
    val publisher: String?,
    val startYear: String?,
    val deck: String?,
    val description: String?,
    val locationCreditsName: List<String>?,
    val characterCreditsName: List<String>?,
    val objectCreditsName: List<String>?,
    val personCreditsName: List<String>?,
    val teamCreditsName: List<String>?,
    val siteDetailUrl: String?
)