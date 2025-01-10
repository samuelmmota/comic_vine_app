package s.m.mota.comicvineandroidnativeapp.ui.model

data class ComicCharacterDetailsUi(
    val id: String?,
    val name: String?,
    val realName: String?,
    val imageUrl: String?,
    val aliases: List<String>?,
    val creatorsUi: List<ComicResourceUi>?,
    val powersUi: List<ComicResourceUi>?,
    val firstAppearedInIssue: ComicResourceUi?,//replace to ComicIssueUI to be a clickable
    val description: String?,
    val publisher: String?,
    val characterType: String?,
    val countOfIssueAppearances: String?,
    val birth: String?,
    val issuesDiedIn: List<ComicResourceUi>?,
    val gender: String?,
    val deck: String?,
    val siteDetailUrl: String?
)