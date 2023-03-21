package app.test.payback.group.data.dao

import app.test.payback.group.domain.entity.ImageDataEntity
import app.test.payback.group.domain.entity.ImageDetailDataEntity
import app.test.payback.group.domain.entity.ImageListDataEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList

internal interface ImageDao {
    suspend fun insertImageList(
        keySearch: String,
        list: List<ImageDataEntity>
    )

    suspend fun searchImageListData(keySearch: String):
            List<ImageDataEntity>

    suspend fun insertImageDetailData(entity: ImageDetailDataEntity)

    suspend fun getImageDetailData(idImg: Long): ImageDetailDataEntity?
}

internal class ImageDaoImpl(private val realm: Realm) : ImageDao {
    override suspend fun insertImageList(keySearch: String, list: List<ImageDataEntity>) {
        realm.writeBlocking {
            copyToRealm(ImageListDataEntity().apply {
                idKeySearch = keySearch
                imageList = list.toRealmList()
            }, UpdatePolicy.ALL)
        }
    }

    override suspend fun searchImageListData(keySearch: String): List<ImageDataEntity> =
        realm.query(ImageListDataEntity::class, "idKeySearch = '$keySearch'").first().find().let {
            it?.imageList ?: arrayListOf()
        }

    override suspend fun insertImageDetailData(entity: ImageDetailDataEntity) {
        realm.writeBlocking {
            copyToRealm(entity, UpdatePolicy.ALL)
        }
    }

    override suspend fun getImageDetailData(idImg: Long) =
        realm.query(ImageDetailDataEntity::class, "id = '$idImg'").first().find()
}