package app.test.payback.group.domain.model

import app.test.payback.group.domain.entity.ImageDetailDataEntity
import kotlinx.serialization.Serializable

@Serializable
data class ImageDetailData(
    val id: Long, val largeImageURL: String, val user: String,
    val tags: String, val likes: Int, val downloads: Int, val comments: Int
) {

    internal fun toEntity() = ImageDetailDataEntity().apply {
        id = this@ImageDetailData.id
        largeImageURL = this@ImageDetailData.largeImageURL
        user = this@ImageDetailData.user
        tags = this@ImageDetailData.tags
        likes = this@ImageDetailData.likes
        downloads = this@ImageDetailData.downloads
        comments = this@ImageDetailData.comments
    }
}