package app.test.payback.group.domain.entity

import app.test.payback.group.domain.model.ImageDetailData
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class ImageDetailDataEntity : RealmObject {
    @PrimaryKey
    var id: Long = 0
    var largeImageURL: String = ""
    var user: String = ""
    var tags: String = ""
    var likes: Int = 0
    var downloads: Int = 0
    var comments: Int = 0

    fun toModel() =
        ImageDetailData(
            id = id,
            largeImageURL = largeImageURL,
            user = user,
            tags = tags,
            likes = likes,
            downloads = downloads,
            comments = comments
        )
}
