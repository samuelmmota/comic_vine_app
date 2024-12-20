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
        @Query("field_list") filedList: String = QueryFieldListBuilder().ID.NAME.ALIASES.PUBLISHER.API_DETAIL_URL.SITE_DETAIL_URL.IMAGE.COUNT_OF_ISSUES.build(),
        //"id,name,aliases,publisher,api_detail_url,site_detail_url,image",
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sort") sort: String? = null,// EX: &sort=field:direction where direction is either asc or desc
        @Query("filter") filter: String? = null,
        /*The result can be filtered by the marked fields in the Fields section below.
          Single filter: &filter=field:value
          Multiple filters: &filter=field:value,field:value
          Date filters: &filter=field:start value|end value (using datetime format)
    */
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
        @Query("field_list") filedList: String? = QueryFieldListBuilder().ID.NAME.RESOURCE_TYPE.PUBLISHER.API_DETAIL_URL.SITE_DETAIL_URL.IMAGE.COUNT_OF_ISSUES.COUNT_OF_EPISODES.START_YEAR.build(),
        //"id,name,resource_type,publisher,api_detail_url,site_detail_url,image,count_of_issues,count_of_episodes,start_year",
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<List<SearchResultModel>>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("issues/")
    suspend fun getIssues(
        @Query("field_list") filedList: String? = QueryFieldListBuilder().ID.NAME.ALIASES.IMAGE.API_DETAIL_URL.ISSUE_NUMBER.build(),
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sort") sort: String? = null,// EX: &sort=field:direction where direction is either asc or desc
        @Query("filter") filter: String? = null,
        /*The result can be filtered by the marked fields in the Fields section below.
          Single filter: &filter=field:value
          Multiple filters: &filter=field:value,field:value
          Date filters: &filter=field:start value|end value (using datetime format)
    */
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
        @Query("field_list") filedList: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sort") sort: String? = null,// EX: &sort=field:direction where direction is either asc or desc
        @Query("filter") filter: String? = null,
        /*The result can be filtered by the marked fields in the Fields section below.
          Single filter: &filter=field:value
          Multiple filters: &filter=field:value,field:value
          Date filters: &filter=field:start value|end value (using datetime format)
    */
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