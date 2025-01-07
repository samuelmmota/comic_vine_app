package s.m.mota.comicvineandroidnativeapp.data.model

import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.ui.component.ComicResourceUi
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicCharacterUi
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicCharacterDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicIssueUi
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicIssueDetailsUi
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicVolumeUi
import s.m.mota.comicvineandroidnativeapp.ui.model.ComicVolumeDetailsUi
import s.m.mota.comicvineandroidnativeapp.utils.Utils.formatedDateMessage

const val blanckComicImageUrl: String =
    "https://comicvine.gamespot.com/a/uploads/screen_kubrick/11122/111222211/6373148-blank.png"

fun ComicResource.toComicResourceUi(): ComicResourceUi {
    return ComicResourceUi(
        id = id,
        name = name,
        thumbnailImageUrl = image?.thumbUrl ?: image?.originalUrl ?: image?.mediumUrl
        ?: blanckComicImageUrl,
        resourceType = resourceType,
        apiId = apiId
    )
}


fun List<ComicResource>.toListOfComicResourceUi(): List<ComicResourceUi>? {
    return this.map { it.toComicResourceUi() }.takeIf {
        it.isNotEmpty()
    }
}

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
        firstAppearedInIssue = firstAppearedInIssue?.toComicResourceUi(),
        description = description,
        publisher = publisher?.name,
        characterType = characterType?.name,
        countOfIssueAppearances = countOfIssueAppearances?.toString(),
        birth = birth,
        issuesDiedIn = issuesDiedIn?.toListOfComicResourceUi(),
        gender = genderDescription,
        deck = deck,
        siteDetailUrl = siteDetailUrl
    )
}

fun ComicCharacter.toComicCharacterUi(): ComicCharacterUi {
    return ComicCharacterUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.originalUrl ?: image?.mediumUrl ?: blanckComicImageUrl,
        aliases = aliasesAsString,
        publisher = publisher?.name,
        characterApiId = characterApiId,
        dateAdded = dateAdded?.let { formatedDateMessage(it) },
        dateLastUpdated = dateLastUpdated?.let { formatedDateMessage(it) }
    )
}

fun ComicIssue.toComicIssueUi(): ComicIssueUi {
    return ComicIssueUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.originalUrl ?: image?.mediumUrl ?: blanckComicImageUrl,
        issueNumber = issueNumber,
        issueApiId = apiId,
        volume = volume?.toComicVolumeUi(),
        dateAdded = dateAdded?.let { formatedDateMessage(it) },
        dateLastUpdated = dateLastUpdated?.let { formatedDateMessage(it) }
    )
}

fun ComicIssue.toComicIssueDetailsUi(): ComicIssueDetailsUi {
    return ComicIssueDetailsUi(
        id = id?.toString(),
        name = name,
        issueNumber = issueNumber,
        issueApiId = apiId,
        aliases = aliasesList,
        description = description,
        associatedImages = listImages,
        coverDate = coverDate,
        dateAdded = dateAdded,
        dateLastUpdated = dateLastUpdated?.let { formatedDateMessage(it) },
        storeDate = storeDate,
        deck = deck,
        hasStaffReview = hasStaffReview ?: false,
        volume = volume?.toComicVolumeUi(),
        personCredits = personCredits?.map {
            val updatedPerson = it.toComicResourceUi()
            updatedPerson.copy(name = "${it.name}/${it.role}")
        }?.takeIf { it.isNotEmpty() },
        characterCredits = characterCredits?.toListOfComicResourceUi(),
        teamCredits = teamCredits?.toListOfComicResourceUi(),
        locationCredits = locationCredits?.toListOfComicResourceUi(),
        conceptCredits = conceptCredits?.toListOfComicResourceUi(),
        objectCredits = objectCredits?.toListOfComicResourceUi(),
        storyArcCredits = storyArcCredits?.toListOfComicResourceUi(),
        firstAppearanceCharacters = firstAppearanceCharacters?.toListOfComicResourceUi(),
        firstAppearanceConcepts = firstAppearanceConcepts?.toListOfComicResourceUi(),
        firstAppearanceLocations = firstAppearanceLocations?.toListOfComicResourceUi(),
        firstAppearanceStoryArcs = firstAppearanceStoryArcs?.toListOfComicResourceUi(),
        firstAppearanceTeams = firstAppearanceTeams?.toListOfComicResourceUi(),
        teamsDisbandedIn = teamsDisbandedIn?.toListOfComicResourceUi(),
        disbandedTeams = disbandedTeams?.toListOfComicResourceUi(),
        charactersDiedIn = charactersDiedIn?.toListOfComicResourceUi()
    )
}

fun ComicVolume.toComicVolumeUi(): ComicVolumeUi {
    return ComicVolumeUi(
        id = id?.toString(),
        name = name,
        imageUrl = image?.originalUrl ?: image?.mediumUrl ?: blanckComicImageUrl,
        volumeApiId = volumeApiId,
        countOfIssues = countOfIssues?.toString(),
        dateLastUpdated = dateLastUpdated?.let { formatedDateMessage(it) },
        lastIssueName = lastIssue?.name,
        publisher = publisher?.name,
        startYear = startYear,
        dateAdded = dateAdded?.let { formatedDateMessage(it) }
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
        dateLastUpdated = dateLastUpdated?.let { formatedDateMessage(it) },
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