package s.m.mota.comicvineandroidnativeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class ComicVineApiResponse<T>(
    @SerializedName("status_code") val statusCode: Int?,
    @SerializedName("error") val error: String?,
    @SerializedName("number_of_total_results") val numberOfTotalResults: Int?,
    @SerializedName("number_of_page_results") val numberOfPageResults: Int?,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("offset") val offset: Int?,
    @SerializedName("results") val results: T?,
    @SerializedName("version") val version: String?
) {
    companion object {
        const val OK = 1
        const val INVALID_API_KEY = 100
        const val OBJECT_NOT_FOUND = 101
        const val ERROR_IN_URL_FORMAT = 102
        const val JSONP_CALLBACK_REQUIRED = 103
        const val FILTER_ERROR = 104
        const val SUBSCRIBER_ONLY = 105
    }

    fun isSuccessful(): Boolean = statusCode == OK
    fun getErrorState(): String {
        return "Error ($statusCode): $error"
    }
}