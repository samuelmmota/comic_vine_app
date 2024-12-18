package s.m.mota.comicvineandroidnativeapp.data.datasource.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import s.m.mota.comicvineandroidnativeapp.R
import java.io.InputStreamReader

class MockInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val responseString = when {
            request.url.encodedPath.contains("character/") -> loadJsonFromRaw(
                R.raw.character_details_mock_api_data, context
            )

            request.url.encodedPath.contains("characters/") -> loadJsonFromRaw(
                R.raw.characters_mock_api_data, context
            )

            request.url.encodedPath.contains("issue/") -> loadJsonFromRaw(
                R.raw.issue_details_mock_api_data, context
            )

            request.url.encodedPath.contains("issues/") -> loadJsonFromRaw(
                R.raw.issues_mock_api_data, context
            )

            request.url.encodedPath.contains("volume/") -> loadJsonFromRaw(
                R.raw.volume_details_mock_api_data, context
            )

            request.url.encodedPath.contains("volumes/") -> loadJsonFromRaw(
                R.raw.volumes_mock_api_data, context
            )

            request.url.encodedPath.contains("search/") -> loadJsonFromRaw(
                R.raw.search_mock_api_data, context
            )

            else -> "{}"
        }

        return Response.Builder().code(200).message("Mock Response").request(request)
            .protocol(okhttp3.Protocol.HTTP_1_1)
            .body(ResponseBody.create("application/json".toMediaTypeOrNull(), responseString))
            .build()
    }

    private fun loadJsonFromRaw(resourceId: Int, context: Context): String {
        return try {
            val inputStream = context.resources.openRawResource(resourceId)
            InputStreamReader(inputStream).readText()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
