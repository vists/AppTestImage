package app.test.payback.group.di

import app.test.payback.group.domain.usecase.ImageDetailUseCase
import app.test.payback.group.domain.usecase.SearchImageUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

internal class KoinMainComponent: KoinComponent {

    val imageListUseCase by inject<SearchImageUseCase>()

    val imageDetailUseCase by inject<ImageDetailUseCase>()

    init {
        startKoin {
            allowOverride(true)
            modules(mainAuthModule)
        }
    }
}