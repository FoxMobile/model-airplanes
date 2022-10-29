package com.modelairplanes.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {
    single { setupFirebaseAuth() }
    single { firestoreUser() }
    single { firestoreCollected() }
}

private fun setupFirebaseAuth() = FirebaseAuth.getInstance()
private fun firestoreUser() = Firebase.firestore.collection("users")
private fun firestoreCollected() = Firebase.firestore.collection("users").document(setupFirebaseAuth().currentUser!!.uid)
