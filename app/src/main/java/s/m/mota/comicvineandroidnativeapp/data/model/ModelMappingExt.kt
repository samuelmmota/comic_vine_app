package s.m.mota.comicvineandroidnativeapp.data.model

import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.ComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details.ComicCharacterDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.ComicIssueUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details.ComicIssueDetailsUi

fun ComicCharacter.toComicCharacterDetailsUi(): ComicCharacterDetailsUi {
    return ComicCharacterDetailsUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Super Name",
        realName = realName ?: "Unknown Real Name",
        imageUrl = image?.superUrl ?: image?.screenLargeUrl ?: image?.screenUrl
        ?: image?.originalUrl
        ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        aliases = aliasesList ?: listOf("No Aliases Known"),
        creators = creatorsList ?: listOf("No Creators Known"),
        powers = powersList ?: listOf("Powers Unknown"),
        firstAppearedInIssue = firstAppearedInIssue?.name ?: "Unknown Information",
        description = description ?: "Description Not Available"
    )
}


fun ComicCharacter.toComicCharacterUi(): ComicCharacterUi {
    return ComicCharacterUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Super Name",
        imageUrl = image?.originalUrl
            ?: image?.mediumUrl
            ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        aliases = aliasesAsString ?: "No Aliases Known",
        issueCount = characterApiId ?: "No Issue Count",
        publisher = publisher?.name ?: "Publisher Unknown",
        characterApiId = characterApiId
    )
}

fun ComicIssue.toComicIssueUi(): ComicIssueUi {
    return ComicIssueUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Issue Name",
        imageUrl = image?.originalUrl
            ?: image?.mediumUrl
            ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        issueNumber = issueNumber ?: "Unknown Issue Number",
        issueApiId = issueApiId
    )
}

fun ComicIssue.toComicIssueDetailsUi(): ComicIssueDetailsUi {
    return ComicIssueDetailsUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Issue Name",
        imageUrl = image?.originalUrl
            ?: image?.mediumUrl
            ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        issueNumber = issueNumber ?: "Unknown Issue Number",
        issueApiId = issueApiId,
        aliases = aliasesList ?: listOf("No Aliases Known"),
        description = description ?: "Description Not Available",
        associatedImages = listImages,
        coverDate = coverDate ?: "Cover Date Unknown",
        dateAdded = dateAdded ?: "Added Date Unknown",
        dateLastUpdated = dateLastUpdated ?: "Last Updated Date Unknown",
        deck = deck ?: "Deck Unknown",
        hasStaffReview = hasStaffReview ?: false,
    )
}