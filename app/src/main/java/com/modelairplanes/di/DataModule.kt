package com.modelairplanes.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single { setupFirebaseAuth() }
    single(named("user")) { firestoreUser() }
    single(named("docs")) { firestoreCollected() }
    single() { firestoreDataUser() }
}

private fun setupFirebaseAuth() = FirebaseAuth.getInstance()
private fun firestoreUser() = Firebase.firestore.collection("users")
private fun firestoreDataUser() = Firebase.firestore.collection("users").document(setupFirebaseAuth().currentUser!!.uid)
private fun firestoreCollected() = Firebase.firestore.collection("users")
    .document(setupFirebaseAuth().currentUser!!.uid)
    .collection("docs")
