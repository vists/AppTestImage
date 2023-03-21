package app.test.payback.group

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
internal expect val MainDispatcher: CoroutineDispatcher

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher
