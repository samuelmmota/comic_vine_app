package s.m.mota.comicvineandroidnativeapp.ui.screens.characters

data class ComicCharacterUi(
    val id: String,
    val name: String,
    val characterApiId: String?,
    val imageUrl: String,
    val aliases: String,
    val issueCount: String,
    val publisher: String
)