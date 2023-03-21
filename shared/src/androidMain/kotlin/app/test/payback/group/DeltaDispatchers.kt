package app.test.payback.group

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.IO

internal actual val MainDispatcher: CoroutineDispatcher = Dispatchers.Main