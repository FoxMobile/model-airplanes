package com.modelairplanes.ui.master.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.modelairplanes.R
import com.modelairplanes.databinding.ActivityMasterDatailBinding
import com.modelairplanes.model.User

class MasterDetailActivity : AppCompatActivity(){

    private var _binding: ActivityMasterDatailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val user: User by lazy { intent.extras?.getSerializable("user") as User }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMasterDatailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation_new)
        navHostFragment.navController.setGraph(graph, bundleOf("user" to user))
        navController = navHostFragment.navController
    }
}