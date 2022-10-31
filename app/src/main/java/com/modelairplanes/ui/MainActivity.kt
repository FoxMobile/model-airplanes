package com.modelairplanes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.modelairplanes.R
import com.modelairplanes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (FirebaseAuth.getInstance().currentUser?.email == "filipenunes.developer@gmail.com") {
            val inflater = navController.navInflater
            val graph = inflater.inflate(R.navigation.mobile_navigation_master)
            navController.graph = graph

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_master
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            binding.navView.inflateMenu(R.menu.bottom_master_nav_menu)
            binding.navView.setupWithNavController(navController)
        } else {

            val inflater = navController.navInflater
            val graph = inflater.inflate(R.navigation.mobile_navigation)
            navController.graph = graph

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_dashboard
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            binding.navView.inflateMenu(R.menu.bottom_nav_menu)
            binding.navView.setupWithNavController(navController)
        }


    }
}