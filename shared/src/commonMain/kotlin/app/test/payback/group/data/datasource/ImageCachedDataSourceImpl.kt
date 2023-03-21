package app.test.payback.group.data.datasource

import app.test.payback.group.data.dao.ImageDao
import app.test.payback.group.domain.datasource.ImageCachedDataSource
import app.test.payback.group.domain.entity.ImageDataEntity
import app.test.payback.group.domain.entity.ImageDetailDataEntity


internal class ImageCachedDataSourceImpl(
    private val imageDao: ImageDao
) : ImageCachedDataSource {
    override suspend fun insertImageDataList(keySearch:String,
                                             list: List<ImageDataEntity>) =
        imageDao.insertImageList(keySearch, list)


    override suspend fun searchImageDataList(keySearch: String) =
        imageDao.searchImageListData(keySearch)

    override suspend fun insertImageDetail(entity: ImageDetailDataEntity)  = imageDao.insertImageDetailData(entity)

    override suspend fun getImageDetail(idImg: Long) = imageDao.getImageDetailData(idImg)

}
