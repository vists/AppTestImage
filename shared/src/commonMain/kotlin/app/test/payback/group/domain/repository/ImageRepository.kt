package app.test.payback.group.domain.repository

import app.test.payback.group.domain.handleerror.ResultWrapper
import app.test.payback.group.domain.model.ImageData
import app.test.payback.group.domain.model.ImageDetailData

interface ImageRepository {

    suspend fun searchCloudImageList(keySearch: String): ResultWrapper<List<ImageData>>

    suspend fun searchCachedImageList(keySearch: String): ResultWrapper<List<ImageData>>

    suspend fun searchCloudAndCacheImageList(keySearch: String): ResultWrapper<List<ImageData>>

    suspend fun getCachedImageDetail(idImg: Long): ResultWrapper<ImageDetailData?>

    suspend fun getCloudAndCacheImageDetail(idImg: Long): ResultWrapper<ImageDetailData>

}
