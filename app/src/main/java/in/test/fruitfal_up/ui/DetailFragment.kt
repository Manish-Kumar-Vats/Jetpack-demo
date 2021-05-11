package `in`.test.fruitfal_up.ui

import `in`.test.fruitfal_up.R
import `in`.test.fruitfal_up.databinding.FragmentDetailBinding
import `in`.test.fruitfal_up.viewmodel.DetailViewModel
import `in`.test.fruitfal_up.viewmodel.DetailViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )

        val args by navArgs<DetailFragmentArgs>()

        val detailModelFactory = DetailViewModelFactory(args.sha)
        val viewModel =
            ViewModelProviders.of(this, detailModelFactory).get(DetailViewModel::class.java)

        binding.detailViewBinding = viewModel

        binding.lifecycleOwner = this


        return binding.root
    }

}