package com.modelairplanes.di

import com.modelairplanes.ui.dashboard.DashboardViewModel
import com.modelairplanes.ui.home.HomeViewModel
import com.modelairplanes.ui.login.LoginViewModel
import com.modelairplanes.ui.master.detail.MasterListViewModel
import com.modelairplanes.ui.master.MasterViewModel
import com.modelairplanes.ui.master.detail.NewViewModel
import com.modelairplanes.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SplashViewModel(
            firebaseAuth = get()
        )
    }

    viewModel {
        LoginViewModel(
            firebaseAuth = get(),
            collectionReference = get(named("user"))
        )
    }

    viewModel {
        DashboardViewModel(
            documentReference = get(),
            collectionReference = get(named("user")),
            firebaseAuth = get()
        )
    }

    viewModel {
        HomeViewModel(
            collectionReference = get(named("docs"))
        )
    }

    viewModel {
        MasterViewModel(
            collectionReference = get(named("user"))
        )
    }

    viewModel {
        MasterListViewModel(
            collectionReference = get(named("user"))
        )
    }

    viewModel {
        NewViewModel(
            collectionReference = get(named("user"))
        )
    }
}