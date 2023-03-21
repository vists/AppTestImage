package app.test.payback.group.data.network

import app.test.payback.group.domain.model.api.ImageListDataApi
import app.test.payback.group.domain.model.api.ImageListDataDetailApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

internal interface ImageApi {
    suspend fun searchImageDataList(tagSearch: String): ImageListDataApi
    suspend fun getImageDetail(idImg: Long): ImageListDataDetailApi
}

internal class ImageApiImpl(private val client: HttpClient) : ImageApi {
    override suspend fun searchImageDataList(tagSearch: String): ImageListDataApi = client.get {
        url {
            parameters.append("q", tagSearch)
        }
    }.body()

    override suspend fun getImageDetail(idImg: Long): ImageListDataDetailApi = client.get {
        url {
            parameters.append("id", idImg.toString())
        }
    }.body()

}