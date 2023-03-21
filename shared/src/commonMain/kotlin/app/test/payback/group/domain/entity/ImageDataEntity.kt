package app.test.payback.group.domain.entity

import app.test.payback.group.domain.model.ImageData
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class ImageDataEntity : RealmObject {
    @PrimaryKey
    var id: Long = 0
    var previewURL: String = ""
    var user: String = ""
    var tags: String = ""

    fun toModel() =
        ImageData(
            id = id,
            previewURL = previewURL,
            user = user,
            tags = tags)
}
