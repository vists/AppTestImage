package app.test.payback.group.domain.entity

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class ImageListDataEntity: RealmObject {
    @PrimaryKey
    var idKeySearch: String = ""
    var imageList: RealmList<ImageDataEntity> = realmListOf()
}