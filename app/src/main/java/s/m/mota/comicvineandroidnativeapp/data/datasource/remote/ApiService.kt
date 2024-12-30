package s.m.mota.comicvineandroidnativeapp.data.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import s.m.mota.comicvineandroidnativeapp.BuildConfig
import s.m.mota.comicvineandroidnativeapp.data.model.issue.ComicIssue
import s.m.mota.comicvineandroidnativeapp.data.model.ComicVolume
import s.m.mota.comicvineandroidnativeapp.data.model.Formats
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel

interface ApiService {
    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("character/{characterApiId}/")
    suspend fun getCharacterDetail(
        @Path("characterApiId") characterId: String,
        @Query("field_list") filedList: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<ComicCharacter>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("characters/")
    suspend fun getCharacters(
        @Query("field_list") filedList: String = QueryFieldListBuilder().ID.NAME.ALIASES.PUBLISHER.API_DETAIL_URL.SITE_DETAIL_URL.IMAGE.COUNT_OF_ISSUES_APPEARANCES.DATE_ADDED.build(),
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("filter") filter: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<List<ComicCharacter>>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("search/")
    suspend fun getSearchResults(
        @Query("query") query: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("resources") resources: String? = null,
        @Query("field_list") filedList: String? = QueryFieldListBuilder().ID.NAME.RESOURCE_TYPE.PUBLISHER.API_DETAIL_URL.SITE_DETAIL_URL.IMAGE.COUNT_OF_ISSUES.COUNT_OF_ISSUES_APPEARANCES.COUNT_OF_EPISODES.START_YEAR.build(),
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<List<SearchResultModel>>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("issues/")
    suspend fun getIssues(
        @Query("field_list") filedList: String? = QueryFieldListBuilder().ID.NAME.ALIASES.IMAGE.API_DETAIL_URL.COUNT_OF_ISSUES.LAST_ISSUE.VOLUME.DATE_ADDED.DATE_LAST_UPDATED.build(),
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("filter") filter: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<List<ComicIssue>>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("issue/{issueApiId}/")
    suspend fun getIssueDetails(
        @Path("issueApiId") issueId: String,
        @Query("field_list") filedList: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<ComicIssue>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("volumes/")
    suspend fun getVolumes(
        @Query("field_list") filedList: String? = QueryFieldListBuilder().ID.NAME.ALIASES.IMAGE.API_DETAIL_URL.PUBLISHER.START_YEAR.VOLUME.COUNT_OF_ISSUES.DATE_ADDED.build(),
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("filter") filter: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<List<ComicVolume>>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("volume/{volumeApiId}/")
    suspend fun getVolumeDetails(
        @Path("volumeApiId") volumeId: String,
        @Query("field_list") filedList: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<ComicVolume>
}