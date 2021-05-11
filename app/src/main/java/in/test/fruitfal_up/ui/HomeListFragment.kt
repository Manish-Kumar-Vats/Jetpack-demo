package `in`.test.fruitfal_up.ui

import `in`.test.fruitfal_up.R
import `in`.test.fruitfal_up.adapter.CommitListener
import `in`.test.fruitfal_up.adapter.HomeListAdapter
import `in`.test.fruitfal_up.databinding.FragmentHomeListBinding
import `in`.test.fruitfal_up.viewmodel.HomeListViewModel
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class HomeListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_list, container, false
        )

        val viewModel: HomeListViewModel =
            ViewModelProvider(this).get(HomeListViewModel::class.java)
        binding.listViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = HomeListAdapter(CommitListener {
            viewModel.onViewClick(it)
        })

        binding.commitRv.adapter = adapter

        viewModel.commitList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { sha ->
            sha?.let {
                this.findNavController()
                    .navigate(HomeListFragmentDirections.actionHomeListFragmentToDetailFragment(sha))
                viewModel.onNavigated()
            }
        })

        Handler().postDelayed({
            binding.shimmerContainer.showShimmer(false)
            binding.shimmerContainer.hideShimmer()
            binding.shimmerContainer.stopShimmer()
        }, 2000)
        return binding.root
    }


}