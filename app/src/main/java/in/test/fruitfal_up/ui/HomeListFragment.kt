package `in`.test.fruitfal_up.ui

import `in`.test.fruitfal_up.databinding.FragmentHomeListBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class HomeListFragment : Fragment() {

    lateinit var binding: FragmentHomeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeListBinding.inflate(layoutInflater)

        return binding.root
    }

}