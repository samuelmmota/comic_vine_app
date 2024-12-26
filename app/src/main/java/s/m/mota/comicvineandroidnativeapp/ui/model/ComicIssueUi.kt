package s.m.mota.comicvineandroidnativeapp.ui.model

data class ComicIssueUi(
    val id: String?,
    val name: String?,
    val volume: ComicVolumeUi?,
    val issueApiId: String?,
    val imageUrl: String,
    val issueNumber: String?,
)