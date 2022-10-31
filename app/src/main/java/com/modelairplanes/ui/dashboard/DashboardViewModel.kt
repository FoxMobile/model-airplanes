package com.modelairplanes.ui.dashboard

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Source
import com.modelairplanes.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel(private val documentReference: DocumentReference,
                         private val collectionReference: CollectionReference,
                         private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun getData() {
        documentReference
            .get(Source.SERVER)
            .addOnSuccessListener { documentSnapshot ->
                _user.value = documentSnapshot.toObject(User::class.java)
            }.addOnFailureListener { }
    }

    fun setUser(name: String, email: String, phone: String) {
        collectionReference.document(firebaseAuth.currentUser!!.uid).set(
            hashMapOf(
                "name" to name,
                "email" to email,
                "phone" to phone,
            )
        ).addOnSuccessListener {
            getData()
        }.addOnFailureListener {

        }
    }
}