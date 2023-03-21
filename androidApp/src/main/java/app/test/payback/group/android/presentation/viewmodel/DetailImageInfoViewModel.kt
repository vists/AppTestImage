package app.test.payback.group.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.test.payback.group.domain.handleerror.ResultWrapper
import app.test.payback.group.domain.model.ImageDetailData
import app.test.payback.group.domain.usecase.ImageDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailImageInfoViewModel @Inject constructor(
    private val imageDetailUC: ImageDetailUseCase
) : ViewModel() {

    private val _imageDetail = MutableStateFlow<ImageDetailData?>(null)
    val imageDetail: StateFlow<ImageDetailData?> = _imageDetail

    fun initArgs(idImg: Long) {
        _imageDetail.value = null
        viewModelScope.launch {
            imageDetailUC.execute(idImg).also { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        _imageDetail.emit(result.value)
                    }
                    else -> result.toString()
                }
            }
        }
    }
}
