package app.test.payback.group.domain.model

import app.test.payback.group.domain.entity.ImageDataEntity
import kotlinx.serialization.Serializable

@Serializable
data class ImageData(val id: Long, val previewURL: String, val user: String, val tags: String) {

   internal fun toEntity() = ImageDataEntity().apply {
        id = this@ImageData.id
        previewURL = this@ImageData.previewURL
        user = this@ImageData.user
        tags = this@ImageData.tags
    }
}