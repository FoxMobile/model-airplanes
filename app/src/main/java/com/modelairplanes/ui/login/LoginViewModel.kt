package com.modelairplanes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val collectionReference: CollectionReference
) :
    ViewModel() {

    private val _createUser = MutableStateFlow(false)
    val createUser: StateFlow<Boolean> = _createUser

    fun setUser() {
        collectionReference.document(firebaseAuth.currentUser!!.uid).set(
            hashMapOf(
                "name" to firebaseAuth.currentUser?.displayName,
                "email" to firebaseAuth.currentUser?.email
            )
        ).addOnSuccessListener {
            _createUser.value = true
        }.addOnFailureListener {
            _createUser.value = false
        }
    }
}