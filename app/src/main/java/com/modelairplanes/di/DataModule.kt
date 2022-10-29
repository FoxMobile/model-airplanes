package com.modelairplanes.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val dataModule = module {
    single { setupFirebaseAuth() }
}

private fun setupFirebaseAuth() = FirebaseAuth.getInstance()