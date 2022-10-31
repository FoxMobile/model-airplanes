package com.modelairplanes.ui.master

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Source
import com.modelairplanes.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MasterViewModel(val collectionReference: CollectionReference) : ViewModel() {

    private val _listItem = MutableStateFlow<ArrayList<User>>(arrayListOf())
    val listItem: StateFlow<ArrayList<User>> = _listItem

    private val items: ArrayList<User> = arrayListOf()

    fun getData() {
        collectionReference
            .get(Source.SERVER)
            .addOnSuccessListener { documentSnapshot ->
                items.addAll(documentSnapshot.toObjects(User::class.java))
                _listItem.value = items
            }.addOnFailureListener { }
    }
}