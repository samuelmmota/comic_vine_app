package s.m.mota.comicvineandroidnativeapp.data.model

import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.ComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details.ComicCharacterDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.ComicIssueUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details.ComicIssueDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.ComicVolumeUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details.ComicVolumeDetailsUi

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
        imageUrl = image?.originalUrl ?: image?.mediumUrl
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
        imageUrl = image?.originalUrl ?: image?.mediumUrl
        ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        issueNumber = issueNumber ?: "Unknown Issue Number",
        issueApiId = issueApiId
    )
}

fun ComicIssue.toComicIssueDetailsUi(): ComicIssueDetailsUi {
    return ComicIssueDetailsUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Issue Name",
        imageUrl = image?.originalUrl ?: image?.mediumUrl
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

fun ComicVolume.toComicVolumeUi(): ComicVolumeUi {
    return ComicVolumeUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Issue Name",
        imageUrl = image?.originalUrl ?: image?.mediumUrl
        ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        volumeApiId = volumeApiId,
        countOfIssues = countOfIssues?.toString() ?: "Number of Issues Unavailable",
        dateAdded = dateAdded ?: "Added Date Unknown",
        dateLastUpdated = dateLastUpdated ?: "Last Updated Date Unknown",
        firstIssueNumber = firstIssue?.issueNumber ?: "Fist Issue Number Unknown",
        firstIssueName = firstIssue?.name ?: "Fist Issue Name Unknown",
        lastIssueNumber = lastIssue?.issueNumber ?: "Last Issue Number Unknown",
        lastIssueName = lastIssue?.name ?: "Last Issue Name Unknown",
        publisher = publisher?.name ?: "Publisher Unknown",
        startYear = startYear ?: "Start Year Unknown",
    )
}

fun ComicVolume.toComicVolumeDetailsUi(): ComicVolumeDetailsUi {
    return ComicVolumeDetailsUi(
        id = id?.toString() ?: "Unknown Id",
        name = name ?: "Unknown Issue Name",
        imageUrl = image?.superUrl ?: image?.screenLargeUrl ?: image?.screenUrl
        ?: image?.originalUrl
        ?: "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png",
        description = description ?: "Description Not Available",
        dateAdded = dateAdded ?: "Added Date Unknown",
        dateLastUpdated = dateLastUpdated ?: "Last Updated Date Unknown",
        deck = deck ?: "Deck Unknown",
        volumeApiId = volumeApiId,
        countOfIssues = countOfIssues?.toString() ?: "Number of Issues Unavailable",
        firstIssue = firstIssue?.toComicIssueUi(),
        lastIssue = lastIssue?.toComicIssueUi(),
        publisher = publisher?.name ?: "Publisher Unknown",
        startYear = startYear ?: "Start Year Unknown",
        locationCreditsName = locationCreditsList ?: listOf("No Locations Credits Known"),
        characterCreditsName = characterCreditsList ?: listOf("No Characters Credits Known"),
        objectCreditsName = objectCreditsList ?: listOf("No Object Credits Known"),
        personCreditsName = personCreditsList ?: listOf("No Person Credits Known"),
        teamCreditsName = teamCreditsList ?: listOf("No Team Credits Known"),
    )
}

