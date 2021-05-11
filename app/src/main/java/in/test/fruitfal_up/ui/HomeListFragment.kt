package `in`.test.fruitfal_up.ui

import `in`.test.fruitfal_up.R
import `in`.test.fruitfal_up.adapter.CommitListener
import `in`.test.fruitfal_up.adapter.HomeListAdapter
import `in`.test.fruitfal_up.databinding.FragmentHomeListBinding
import `in`.test.fruitfal_up.viewmodel.HomeListViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeListFragment : Fragment() {


    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
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

        val layoutManager = binding.commitRv.layoutManager as LinearLayoutManager

        binding.commitRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    Log.i("TAG", "visibleItemCount $visibleItemCount")
                    Log.i("TAG", "lastVisibleItemPosition $lastVisibleItem")
                    Log.i("TAG", "totalItemCount $totalItemCount")
                    if (viewModel.isScrolling.value == true) {
                        viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
                    }
                }
            }
        })

        viewModel.isScrolling.observe(viewLifecycleOwner, Observer {
            if (!it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        })
        viewModel.isDataAvailable.observe(viewLifecycleOwner, Observer {
            if (!it) {
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.emptyView.visibility = View.GONE
            }
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

        viewModel.isLoadedFirstTime.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.shimmerContainer.showShimmer(false)
                binding.shimmerContainer.hideShimmer()
                binding.shimmerContainer.stopShimmer()
            }
        })
        return binding.root
    }


}