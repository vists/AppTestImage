package app.test.payback.group.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import app.test.payback.group.android.R
import app.test.payback.group.android.databinding.FragmentSearchImageListBinding
import app.test.payback.group.android.presentation.extension.hideKeyboard
import app.test.payback.group.android.presentation.viewmodel.SearchImageViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchImageListFragment : Fragment() {

    private val viewModel: SearchImageViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private val navController by lazy { findNavController(this) }
    private var dialogDetail: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.onViewCreated()
        initNavigate()
        return FragmentSearchImageListBinding.inflate(
            layoutInflater,
            container,
            false
        ).apply {
            lifecycleOwner = this@SearchImageListFragment
            vm = viewModel
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogDetail?.dismiss()
    }

    private fun initNavigate() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigate.collect {
                it?.also {
                    when (it.first) {
                        R.id.action_searchImageListFragment_to_detailImageInfoFragment -> {
                            navController
                                .navigate(
                                    SearchImageListFragmentDirections.actionSearchImageListFragmentToDetailImageInfoFragment(
                                        it.second as Long
                                    )
                                )
                        }
                    }
                }
            }

        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dialogDetail.collect {
                it?.also {
                    dialogDetail?.dismiss()
                    hideKeyboard()
                    dialogDetail = MaterialAlertDialogBuilder(requireContext())
                        .setMessage(getString(R.string.question_see_detail_dialog, it))
                        .setNegativeButton(getString(android.R.string.cancel)) { _, _ ->
                            viewModel.clickDetailCancel()
                        }
                        .setCancelable(false)
                        .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                            viewModel.clickDetailOk(
                                it
                            )
                        }
                        .show()
                }
            }
        }
    }

}