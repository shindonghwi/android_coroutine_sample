package wolf.shin.studycoroutine.naver_search.api

data class ImageSearchResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeheight: String,
    val sizewidth: String
)