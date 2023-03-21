package app.test.payback.group.android.presentation.bind

import android.widget.ImageView
import android.widget.Toolbar
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.test.payback.group.android.R
import app.test.payback.group.android.presentation.adapter.BaseDataBindAdapter
import app.test.payback.group.android.presentation.viewmodel.SearchImageViewModel
import app.test.payback.group.domain.model.ImageData
import com.squareup.picasso.Picasso

@BindingAdapter("setDataList", "setViewModel")
fun RecyclerView.setAdapterBindData(list: List<ImageData>?, vm: SearchImageViewModel) {
    if (adapter == null) {
        layoutManager = LinearLayoutManager(context)
        adapter = BaseDataBindAdapter<ImageData>(
            R.layout.item_image,
            BR.imageData,
            viewModel = vm
        )
    }
    list?.also {
        (adapter as BaseDataBindAdapter<ImageData>).dataList = it
    }
}

@BindingAdapter("setUrlImage")
fun ImageView.setUrlImage(url: String?) {
    if (!url.isNullOrEmpty())
        Picasso.get().load(url).into(this)
}


