package wolf.shin.studycoroutine.naver_search.api

import wolf.shin.studycoroutine.naver_search.model.Item

data class ImageSearchResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>
)