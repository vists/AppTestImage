package app.test.payback.group.domain.usecase

import app.test.payback.group.ApplicationDispatcher
import app.test.payback.group.domain.handleerror.ResultWrapper
import app.test.payback.group.domain.repository.ImageRepository
import kotlinx.coroutines.withContext


class SearchImageUseCase(private val repository: ImageRepository) {
    suspend fun execute(tagName: String) = withContext(ApplicationDispatcher) {
        repository.searchCloudAndCacheImageList(tagName)
            .let {
                when (it) {
                    is ResultWrapper.Error.GeneralError -> repository.searchCachedImageList(tagName)
                    else -> it
                }
            }
    }
}
