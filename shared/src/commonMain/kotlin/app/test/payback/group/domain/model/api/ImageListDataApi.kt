package app.test.payback.group.domain.model.api

import app.test.payback.group.domain.model.ImageData
import app.test.payback.group.domain.model.ImageDetailData
import kotlinx.serialization.Serializable

@Serializable
internal open class ImageListDataApi(val total: Int, val totalHits: Int, val hits: List<ImageData>)

@Serializable
internal class ImageListDataDetailApi(val hits: List<ImageDetailData>)
