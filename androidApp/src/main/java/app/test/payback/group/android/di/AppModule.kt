package app.test.payback.group.android.di

import app.test.payback.group.main.ImageDataLib
import app.test.payback.group.main.ImageDataLibImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideImageDataLib(
    ): ImageDataLib {
        return ImageDataLibImpl()
    }
}