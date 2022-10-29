package com.modelairplanes.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.modelairplanes.databinding.FragmentDashboardBinding
import com.modelairplanes.ui.home.HomeAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        dashboardViewModel.getData()
        binding.update.setOnClickListener {
            dashboardViewModel.setUser(binding.name.text.toString(),
                binding.email.text.toString(),
                binding.phone.text.toString())
        }
    }

    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            dashboardViewModel.user.collect { user ->
                binding.name.setText(user?.name)
                binding.email.setText(user?.email)
                binding.phone.setText(user?.phone)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}