package app.test.payback.group.android.di

import androidx.databinding.library.baseAdapters.BR
import app.test.payback.group.android.R
import app.test.payback.group.android.presentation.adapter.BaseDataBindAdapter
import app.test.payback.group.android.presentation.viewmodel.SearchImageViewModel
import app.test.payback.group.domain.model.ImageData
import app.test.payback.group.domain.usecase.ImageDetailUseCase
import app.test.payback.group.domain.usecase.SearchImageUseCase
import app.test.payback.group.main.ImageDataLib
import app.test.payback.group.main.ImageDataLibImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideSearchImageListUseCase(
        imageDataLib: ImageDataLib
    ): SearchImageUseCase {
        return imageDataLib.getImageListUseCase()
    }

    @Provides
    fun provideImageDetailUseCase(
        imageDataLib: ImageDataLib
    ): ImageDetailUseCase {
        return imageDataLib.getImageDetailUseCase()
    }
}