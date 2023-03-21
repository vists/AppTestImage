package app.test.payback.group.main

import app.test.payback.group.domain.usecase.ImageDetailUseCase
import app.test.payback.group.domain.usecase.SearchImageUseCase


/**
 * ImageDataLib class is intended get actual from backend image's list
 * and cached image's list from local database.
 */
interface ImageDataLib {

    fun getImageListUseCase(): SearchImageUseCase

    fun getImageDetailUseCase(): ImageDetailUseCase
}