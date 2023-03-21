package app.test.payback.group.main

import app.test.payback.group.di.KoinMainComponent

/**
 * Entry point to the library. This instance holds all the necessary data
 * to work with the library.
 */
 class ImageDataLibImpl: ImageDataLib {
    companion object{
        private val mainComponent = KoinMainComponent()
    }

    override fun getImageListUseCase() = mainComponent.imageListUseCase

    override fun getImageDetailUseCase() = mainComponent.imageDetailUseCase
}