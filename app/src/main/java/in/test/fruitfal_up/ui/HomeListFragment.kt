package `in`.test.fruitfal_up.ui

import `in`.test.fruitfal_up.R
import `in`.test.fruitfal_up.adapter.CommitListener
import `in`.test.fruitfal_up.adapter.HomeListAdapter
import `in`.test.fruitfal_up.databinding.FragmentHomeListBinding
import `in`.test.fruitfal_up.viewmodel.HomeListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest


class HomeListFragment : Fragment() {

    lateinit var viewModel: HomeListViewModel
    lateinit var binding: FragmentHomeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_list, container, false
        )
        viewModel =
            ViewModelProvider(this).get(HomeListViewModel::class.java)
        binding.listViewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = HomeListAdapter(CommitListener {
            viewModel.onViewClick(it)
        })

        binding.commitRv.apply {
            layoutManager = LinearLayoutManager(context)
//            val decoration =
//                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//            addItemDecoration(decoration)
            binding.commitRv.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getPagingList().collectLatest {
                binding.shimmerContainer.stopShimmer()
                binding.shimmerContainer.hideShimmer()
                adapter.submitData(it)
            }
        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { sha ->
            sha?.let {
                this.findNavController()
                    .navigate(
                        HomeListFragmentDirections.actionHomeListFragmentToDetailFragment(
                            it
                        )
                    )
                viewModel.onNavigated()
            }
        })

        return binding.root
    }


}