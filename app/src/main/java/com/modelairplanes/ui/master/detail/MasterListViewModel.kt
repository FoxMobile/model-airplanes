package com.modelairplanes.ui.master.detail

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.modelairplanes.model.Payment
import com.modelairplanes.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.firebase.Timestamp


class MasterListViewModel(val collectionReference: CollectionReference) : ViewModel() {

    private val _listItem = MutableStateFlow<ArrayList<Payment>>(arrayListOf())
    val listItem: StateFlow<ArrayList<Payment>> = _listItem

    private val items: ArrayList<Payment> = arrayListOf()

    fun getData(user: User) {
        collectionReference.document(user.uid!!).collection("docs").limit(10)
            .orderBy("limit_payment", Query.Direction.ASCENDING)
            .get(Source.SERVER)
            .addOnSuccessListener { documentSnapshot ->
                items.addAll(documentSnapshot.documents.map {
                    Payment(
                        id = it.id,
                        is_payment = it.data?.get("is_payment") as Boolean,
                        limit_payment = (it.data?.get("limit_payment") as Timestamp).toDate(),
                        url_document = it.data?.get("url_document") as String
                    )
                })
                _listItem.value = items
            }.addOnFailureListener { }
    }
}