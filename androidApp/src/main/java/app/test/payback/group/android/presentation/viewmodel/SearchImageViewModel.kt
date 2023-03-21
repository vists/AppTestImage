package app.test.payback.group.android.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.test.payback.group.android.R
import app.test.payback.group.domain.handleerror.ResultWrapper
import app.test.payback.group.domain.model.ImageData
import app.test.payback.group.domain.usecase.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val searchImageUC: SearchImageUseCase
) : ViewModel() {

    val keySearch = MutableStateFlow("fruits")

    private val _imageList = MutableStateFlow(emptyList<ImageData>())
    val imageList: StateFlow<List<ImageData>> = _imageList

    private val _navigate = MutableStateFlow<Pair<Int, Any?>?>(null)
    val navigate: StateFlow<Pair<Int, Any?>?> = _navigate

    private val _dialogDetail = MutableStateFlow<Long?>(null)
    val dialogDetail: StateFlow<Long?> = _dialogDetail

    private var isFragmentCreated = true

    init {
        viewModelScope.launch {
            keySearch.collectLatest {
                delay(2000)
                searchImageUC.execute(it).also { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            _imageList.emit(result.value)
                        }
                        else -> result.toString()
                    }
                }
            }
        }
    }

    fun clickImageItem(imageData: ImageData) {
        viewModelScope.launch {
            _dialogDetail.emit(imageData.id)
        }
    }

    fun onCreate() {
        isFragmentCreated = true
    }

    fun onViewCreated() {
        viewModelScope.launch {
            if (!isFragmentCreated) {
                if (_dialogDetail.value != null) {
                    _dialogDetail.value = null
                }
                if (_navigate.value != null) {
                    _navigate.value = null
                }
            } else {
                if (_navigate.value != null) {
                    _navigate.value = null
                    _dialogDetail.value = null
                }
            }
            isFragmentCreated = false
        }
    }

    fun clickDetailCancel() {
        viewModelScope.launch {
            _dialogDetail.value = null
        }
    }

    fun clickDetailOk(idImg: Long) {
        viewModelScope.launch {
            _dialogDetail.value = null
            _navigate.emit(
                Pair(
                    R.id.action_searchImageListFragment_to_detailImageInfoFragment,
                    idImg
                )
            )
        }
    }
}