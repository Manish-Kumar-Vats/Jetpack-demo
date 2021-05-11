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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeListFragment : Fragment() {


    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

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
//        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
//        binding.commitRv.addItemDecoration(decoration)
//        val layoutManager = binding.commitRv.layoutManager as LinearLayoutManager
//        binding.commitRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val totalItemCount = layoutManager.itemCount
//                val visibleItemCount = layoutManager.childCount
//                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
//
//                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
//            }
//        })



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

        viewModel.isLoaded.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.shimmerContainer.showShimmer(false)
                binding.shimmerContainer.hideShimmer()
                binding.shimmerContainer.stopShimmer()
            }
        })
//        Handler().postDelayed({
//            viewModel.isScrolledEnd.value = true
//        }, 4000)
//        viewModel.isScrolledEnd.observe(viewLifecycleOwner, Observer {
//            if (it) {
//
//            }
//        })

        return binding.root
    }


}