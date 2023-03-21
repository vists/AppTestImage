package app.test.payback.group.domain.usecase

import app.test.payback.group.ApplicationDispatcher
import app.test.payback.group.domain.handleerror.ResultWrapper
import app.test.payback.group.domain.repository.ImageRepository
import kotlinx.coroutines.withContext


class ImageDetailUseCase(private val repository: ImageRepository) {
    suspend fun execute(idImg: Long) = withContext(ApplicationDispatcher) {
        repository.getCloudAndCacheImageDetail(idImg)
            .let {
                when (it) {
                    is ResultWrapper.Error.GeneralError -> repository.getCachedImageDetail(idImg)
                    else -> it
                }
            }
    }
}
