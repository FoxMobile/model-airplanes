package com.modelairplanes.ui.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class SplashViewModel(val firebaseAuth: FirebaseAuth): ViewModel() {

    fun isCurrentUserAuth() = firebaseAuth.currentUser != null
}