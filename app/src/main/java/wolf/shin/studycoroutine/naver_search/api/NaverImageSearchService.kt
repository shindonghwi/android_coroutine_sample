package wolf.shin.studycoroutine.naver_search.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NaverImageSearchService {

    @GET("/v1/search/image")
    suspend fun getImages(
        @Query("query") query: String, // 필수
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("filter") filter: String? = null
    ): ImageSearchResponse
}