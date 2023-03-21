package app.test.payback.group.data.datasource

import app.test.payback.group.data.network.ImageApi
import app.test.payback.group.domain.datasource.ImageRemoteDataSource

internal class ImageRemoteDataSourceImpl(
    private val api: ImageApi
) : ImageRemoteDataSource {

    override suspend fun searchImageDataList(keySearch: String) =
        api.searchImageDataList(keySearch).hits

    override suspend fun getImageDetail(idImg: Long) = api.getImageDetail(idImg).hits[0]
}
