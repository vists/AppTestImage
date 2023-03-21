package app.test.payback.group.di

import app.test.payback.group.ApplicationDispatcher
import app.test.payback.group.data.dao.ImageDao
import app.test.payback.group.data.dao.ImageDaoImpl
import app.test.payback.group.data.datasource.ImageCachedDataSourceImpl
import app.test.payback.group.data.datasource.ImageRemoteDataSourceImpl
import app.test.payback.group.data.network.ImageApi
import app.test.payback.group.data.network.ImageApiImpl
import app.test.payback.group.data.repository.ImageRepositoryImpl
import app.test.payback.group.di.provider.KtorProvider
import app.test.payback.group.di.provider.RealmProvider
import app.test.payback.group.domain.datasource.ImageCachedDataSource
import app.test.payback.group.domain.datasource.ImageRemoteDataSource
import app.test.payback.group.domain.repository.ImageRepository
import app.test.payback.group.domain.usecase.ImageDetailUseCase
import app.test.payback.group.domain.usecase.SearchImageUseCase
import org.koin.dsl.module

/**
 * Collect modules for Koin
 */

internal val mainAuthModule = module {
    single { KtorProvider()}
    single{ RealmProvider()}
    single { ApplicationDispatcher }
    single <ImageApi>{ImageApiImpl((get() as KtorProvider).httpClient) }
    single <ImageDao>{ImageDaoImpl((get() as RealmProvider).realm) }
    single <ImageRemoteDataSource>{ ImageRemoteDataSourceImpl(get()) }
    single <ImageCachedDataSource>{ ImageCachedDataSourceImpl(get()) }
    single <ImageRepository>{ ImageRepositoryImpl(get(),get()) }
    single { SearchImageUseCase(get()) }
    single { ImageDetailUseCase(get()) }
}