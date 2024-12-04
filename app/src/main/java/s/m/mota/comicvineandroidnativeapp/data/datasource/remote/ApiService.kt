package s.m.mota.comicvineandroidnativeapp.data.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import s.m.mota.comicvineandroidnativeapp.BuildConfig
import s.m.mota.comicvineandroidnativeapp.data.model.Formats
import s.m.mota.comicvineandroidnativeapp.data.model.character.ComicCharacter
import s.m.mota.comicvineandroidnativeapp.data.model.response.ComicVineApiResponse
import s.m.mota.comicvineandroidnativeapp.data.model.response.SearchResultModel

interface ApiService {
    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("4346cd3c-ef48-4e29-9a00-000c04129413/{characterApiId}/")
    //@GET("character/{characterApiId}/")
    suspend fun getCharacterDetail(
        @Path("characterApiId") characterId: String,
        @Query("field_list") filedList: String? = null,
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<ComicCharacter>

    @Headers("User-Agent: ComicVineClient/1.0")
    @GET("3169e2e8-7d91-49f9-86a5-da2b36262114/")// 100 items
    //@GET("characters/")
    suspend fun getCharacters(
        @Query("field_list") filedList: String = QueryFieldListBuilder().ID.NAME.ALIASES.PUBLISHER.API_DETAIL_URL.SITE_DETAIL_URL.IMAGE.COUNT_OF_ISSUES.build(),
        //"id,name,aliases,publisher,api_detail_url,site_detail_url,image",
        @Query("limit") limit: Int = 100,
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
    @GET("8d3aed68-927e-4d99-9edd-a9d458368aec/")
    //@GET("search/")
    suspend fun getSearchResults(
        @Query("query") query: String,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int? = null,
        @Query("resources") resources: String? = null,
        @Query("field_list") filedList: String? = QueryFieldListBuilder().ID.NAME.RESOURCE_TYPE.PUBLISHER.API_DETAIL_URL.SITE_DETAIL_URL.IMAGE.COUNT_OF_ISSUES.COUNT_OF_EPISODES.START_YEAR.build(),
        //"id,name,resource_type,publisher,api_detail_url,site_detail_url,image,count_of_issues,count_of_episodes,start_year",
        @Query("format") format: String = Formats.JSON.name,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): ComicVineApiResponse<List<SearchResultModel>>
}