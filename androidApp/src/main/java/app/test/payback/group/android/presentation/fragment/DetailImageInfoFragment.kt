package app.test.payback.group.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.test.payback.group.android.R
import app.test.payback.group.android.databinding.FragmentDetailImageInfoBinding
import app.test.payback.group.android.presentation.viewmodel.DetailImageInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailImageInfoFragment : Fragment() {

    private val viewModel: DetailImageInfoViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private val args: DetailImageInfoFragmentArgs by navArgs()
    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentDetailImageInfoBinding.inflate(
            layoutInflater,
            container,
            false
        ).apply {
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            viewModel.initArgs(args.idImg)
            lifecycleOwner = this@DetailImageInfoFragment
            vm = viewModel
        }.root
    }
}