package app.test.payback.group.domain.handleerror

import io.ktor.client.plugins.*


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    sealed class Error : ResultWrapper<Nothing>() {
        data class HttpError(val codeData: Int? = null, val errorData: Any? = null) : Error()
        data class GeneralError(val throwable: Throwable) : Error()
    }
}

internal suspend fun <T> runSafeWithResult(action: suspend () -> T): ResultWrapper<T> =
    runCatching {
        ResultWrapper.Success(action())
    }.getOrElse {
        when (it) {
            is ClientRequestException -> ResultWrapper.Error.HttpError(
                it.response.status.value,
                it.message
            )
            else -> ResultWrapper.Error.GeneralError(it as Exception)
        }
    }