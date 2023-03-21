package app.test.payback.group

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@SharedImmutable
internal actual val MainDispatcher: CoroutineDispatcher = Dispatchers.Main

@SharedImmutable
internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default
