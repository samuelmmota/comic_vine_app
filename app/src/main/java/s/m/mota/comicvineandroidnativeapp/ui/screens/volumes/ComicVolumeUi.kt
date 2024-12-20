package s.m.mota.comicvineandroidnativeapp.ui.screens.volumes

data class ComicVolumeUi(
    val id: String,
    val name: String,
    val volumeApiId: String?,
    val imageUrl: String,
    val countOfIssues: String,
    val dateAdded: String,
    val dateLastUpdated: String,
    val firstIssueNumber: String,
    val firstIssueName: String,
    val lastIssueNumber: String,
    val lastIssueName: String,
    val publisher: String,
    val startYear: String,
)
