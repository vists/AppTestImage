package app.test.payback.group.data.repository

import app.test.payback.group.domain.datasource.ImageCachedDataSource
import app.test.payback.group.domain.datasource.ImageRemoteDataSource
import app.test.payback.group.domain.handleerror.ResultWrapper
import app.test.payback.group.domain.handleerror.runSafeWithResult
import app.test.payback.group.domain.model.ImageDetailData
import app.test.payback.group.domain.repository.ImageRepository

internal class ImageRepositoryImpl(
    private val remote: ImageRemoteDataSource,
    private val cache: ImageCachedDataSource
) : ImageRepository {

    override suspend fun searchCloudImageList(keySearch: String) =
        runSafeWithResult {
            remote.searchImageDataList(keySearch)
        }

    override suspend fun searchCachedImageList(keySearch: String) =
        runSafeWithResult {
            cache.searchImageDataList(keySearch).map { it.toModel() }
        }

    override suspend fun searchCloudAndCacheImageList(keySearch: String) =
        runSafeWithResult {
            remote.searchImageDataList(keySearch).also { list ->
                if (list.isNotEmpty()) {
                    cache.insertImageDataList(keySearch, list.map { it.toEntity() })
                }
            }
        }

    override suspend fun getCachedImageDetail(idImg: Long): ResultWrapper<ImageDetailData?> =
        runSafeWithResult {
            cache.getImageDetail(idImg)?.toModel()
        }

    override suspend fun getCloudAndCacheImageDetail(idImg: Long): ResultWrapper<ImageDetailData> =
        runSafeWithResult {
            remote.getImageDetail(idImg).also {
                cache.insertImageDetail(it.toEntity())
            }
        }
}
