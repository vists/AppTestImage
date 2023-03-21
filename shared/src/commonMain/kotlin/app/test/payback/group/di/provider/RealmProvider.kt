package app.test.payback.group.di.provider

import app.test.payback.group.domain.entity.ImageDataEntity
import app.test.payback.group.domain.entity.ImageDetailDataEntity
import app.test.payback.group.domain.entity.ImageListDataEntity
import app.test.payback.group.domain.model.ImageDetailData
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal class RealmProvider {
    private val config = RealmConfiguration.create(
        schema = setOf(
            ImageListDataEntity::class,
            ImageDataEntity::class,
            ImageDetailDataEntity::class
        )
    )
    val realm: Realm = Realm.open(config)
}