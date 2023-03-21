package app.test.payback.group.domain.datasource

import app.test.payback.group.domain.entity.ImageDataEntity
import app.test.payback.group.domain.entity.ImageDetailDataEntity
import app.test.payback.group.domain.model.ImageData
import app.test.payback.group.domain.model.ImageDetailData

internal interface ImageRemoteDataSource {
    suspend fun searchImageDataList(keySearch: String): List<ImageData>
    suspend fun getImageDetail(idImg: Long):  ImageDetailData
}

internal interface ImageCachedDataSource {
    suspend fun insertImageDataList(
        keySearch:String,
        list: List<ImageDataEntity>
    )

    suspend fun insertImageDetail(
        entity: ImageDetailDataEntity
    )

    suspend fun searchImageDataList(keySearch: String):
            List<ImageDataEntity>

    suspend fun getImageDetail(idImg: Long):  ImageDetailDataEntity?
}
