package s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_detail

import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter

data class ComicCharacterUi(
    val id: String,
    val name: String,
    val realName: String,
    val imageUrl: String,
    val aliases: List<String>,
    val creators: List<String>,
    val powers: List<String>,
    val firstAppearedInIssue: String,
    val description: String,
)

fun CharacterDetailsViewModel.toComicCharacter(characterDetails: ComicCharacter?): ComicCharacterUi {
    return ComicCharacterUi(
        id = characterDetails?.id?.toString() ?: "Unknown Id",
        name = characterDetails?.name ?: "Unknown Super Name",
        realName = characterDetails?.realName ?: "Unknown Real Name",
        imageUrl = characterDetails?.getImageUrl()
            ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        aliases = characterDetails?.getAliasesAsList() ?: listOf("No Aliases Known"),
        creators = characterDetails?.getCreatorsAsList() ?: listOf("No Creators Known"),
        powers = characterDetails?.getPowersAsList() ?: listOf("Powers Unknown"),
        firstAppearedInIssue = characterDetails?.firstAppearedInIssue?.name
            ?: "Unknown Information",
        description = characterDetails?.description ?: "Description Not Available"
    )
}
