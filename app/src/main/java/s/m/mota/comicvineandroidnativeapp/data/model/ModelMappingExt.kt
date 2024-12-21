package s.m.mota.comicvineandroidnativeapp.data.model

import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.ComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.characters.character_details.ComicCharacterDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.ComicIssueUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.issues.issue_details.ComicIssueDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.ComicVolumeUi
import s.m.mota.comicvineandroidnativeapp.ui.screens.volumes.volume_details.ComicVolumeDetailsUi
import s.m.mota.comicvineandroidnativeapp.utils.Utils.updatedDateMessage

const val blanckComicImageUrl: String =
    "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png"

fun ComicCharacter.toComicCharacterDetailsUi(): ComicCharacterDetailsUi {
    return ComicCharacterDetailsUi(
        id = id?.toString(),
        name = name,
        realName = realName,
        imageUrl = image?.superUrl ?: image?.screenLargeUrl ?: image?.screenUrl
        ?: image?.originalUrl ?: blanckComicImageUrl,
        aliases = aliasesList,
        creators = creatorsList,
        powers = powersList,
        firstAppearedInIssue = firstAppearedInIssue?.name,
        description = description
    )
}

fun ComicCharacter.toComicCharacterUi(): ComicCharacterUi {
    return ComicCharacterUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.originalUrl ?: image?.mediumUrl ?: blanckComicImageUrl,
        aliases = aliasesAsString,
        issueCount = characterApiId,
        publisher = publisher?.name,
        characterApiId = characterApiId
    )
}

fun ComicIssue.toComicIssueUi(): ComicIssueUi {
    return ComicIssueUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.originalUrl ?: image?.mediumUrl ?: blanckComicImageUrl,
        issueNumber = issueNumber,
        issueApiId = issueApiId
    )
}

fun ComicIssue.toComicIssueDetailsUi(): ComicIssueDetailsUi {
    return ComicIssueDetailsUi(
        id = id?.toString(),
        name = name,
        issueNumber = issueNumber,
        issueApiId = issueApiId,
        aliases = aliasesList,
        description = description,
        associatedImages = listImages,
        coverDate = coverDate,
        dateAdded = dateAdded,
        dateLastUpdated = dateLastUpdated,
        deck = deck,
        hasStaffReview = hasStaffReview ?: false,
    )
}

fun ComicVolume.toComicVolumeUi(): ComicVolumeUi {
    return ComicVolumeUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.originalUrl ?: image?.mediumUrl ?: blanckComicImageUrl,
        volumeApiId = volumeApiId,
        countOfIssues = countOfIssues?.toString(),
        dateLastUpdated = dateLastUpdated?.let { updatedDateMessage(it) },
        lastIssueName = lastIssue?.name,
        publisher = publisher?.name,
        startYear = startYear,
    )
}

fun ComicVolume.toComicVolumeDetailsUi(): ComicVolumeDetailsUi {
    return ComicVolumeDetailsUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.superUrl ?: image?.screenLargeUrl ?: image?.screenUrl
        ?: image?.originalUrl ?: blanckComicImageUrl,
        description = description,
        dateAdded = dateAdded,
        dateLastUpdated = dateLastUpdated,
        deck = deck,
        volumeApiId = volumeApiId,
        countOfIssues = countOfIssues?.toString(),
        firstIssue = firstIssue?.toComicIssueUi(),
        lastIssue = lastIssue?.toComicIssueUi(),
        publisher = publisher?.name,
        startYear = startYear,
        locationCreditsName = locationCreditsList,
        characterCreditsName = characterCreditsList,
        objectCreditsName = objectCreditsList,
        personCreditsName = personCreditsList,
        teamCreditsName = teamCreditsList
    )
}