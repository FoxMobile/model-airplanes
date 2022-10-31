package com.modelairplanes.ui.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.modelairplanes.R
import com.modelairplanes.databinding.FragmentMasterBinding
import com.modelairplanes.model.User
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterFragment: Fragment(), MasterInteraction {

    private var _binding: FragmentMasterBinding? = null
    private val binding get() = _binding!!

    private val masterViewModel: MasterViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMasterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        masterViewModel.getData()
    }

    private fun initUI() = with(viewLifecycleOwner.lifecycleScope) {
        launch {
            masterViewModel.listItem.collect { items ->
                binding.recycler.adapter =  MasterAdapter(items, this@MasterFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickUser(user: User) {
        findNavController().navigate(R.id.masterDetailActivity, bundleOf("user" to user))
    }
}