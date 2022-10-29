package com.modelairplanes.ui.home

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel(val collectionReference: CollectionReference) : ViewModel() {

    private val _listItem = MutableStateFlow<ArrayList<Payment>>(arrayListOf())
    val listItem: StateFlow<ArrayList<Payment>> = _listItem

    private val items: ArrayList<Payment> = arrayListOf()

    fun getData() {
        collectionReference.limit(10).orderBy("limit_payment", Query.Direction.ASCENDING)
            .get(Source.SERVER)
            .addOnSuccessListener { documentSnapshot ->
                items.addAll(documentSnapshot.toObjects(Payment::class.java))
                _listItem.value = items
            }.addOnFailureListener { }
    }
}