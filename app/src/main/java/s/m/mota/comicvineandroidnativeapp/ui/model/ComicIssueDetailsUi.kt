package s.m.mota.comicvineandroidnativeapp.ui.model

data class ComicIssueDetailsUi(
    val id: String?,
    val name: String?,
    val aliases: List<String>?,
    val issueApiId: String?,
    val issueNumber: String?,
    val description: String?,
    val associatedImages: List<String>,
    val coverDate : String?,
    val dateAdded: String?,
    val dateLastUpdated: String?,
    val deck: String?,
    //val firstApperanceCharacters: ,
    val hasStaffReview: Boolean,
)
