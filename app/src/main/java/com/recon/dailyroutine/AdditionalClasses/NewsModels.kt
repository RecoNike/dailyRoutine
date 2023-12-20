package com.recon.dailyroutine.AdditionalClasses
import com.google.gson.annotations.SerializedName

data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)

data class NewsItem(
    val uuid: String,
    val title: String,
    val description: String,
    val keywords: String,
    val snippet: String,
    val url: String,
    @SerializedName("image_url") val imageUrl: String,
    val language: String,
    @SerializedName("published_at") val publishedAt: String,
    val source: String,
    val categories: List<String>,
    @SerializedName("relevance_score") val relevanceScore: Double
)

data class NewsResponse(
    val meta: Meta,
    val data: List<NewsItem>
)
