package com.modelairplanes.ui.master.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.modelairplanes.R
import com.modelairplanes.databinding.FragmentHomeBinding
import com.modelairplanes.databinding.FragmentMasterListBinding
import com.modelairplanes.model.Payment
import com.modelairplanes.model.User
import com.modelairplanes.ui.home.HomeAdapter
import com.modelairplanes.ui.home.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterListFragment : Fragment(), MasterDetailInteraction  {

    private var _binding: FragmentMasterListBinding? = null
    private val binding get() = _binding!!

    private val user: User by lazy { requireArguments().getSerializable("user") as User }

    private val masterListViewModel: MasterListViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMasterListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        masterListViewModel.getData(user)

        binding.addMore.setOnClickListener {
            findNavController().navigate(
                R.id.navigation_new,
                bundleOf("user" to user)
            )
        }
    }

    private fun initUI() = with(viewLifecycleOwner.lifecycleScope) {
        launch {
            masterListViewModel.listItem.collect { items ->
                binding.recycler.adapter =  MasterListAdapter(items, this@MasterListFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickInfo(payment: Payment) {
        findNavController().navigate(
            R.id.navigation_new,
            bundleOf("user" to user, "payment" to payment)
        )
    }
}