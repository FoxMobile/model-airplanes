package com.modelairplanes.ui.master.detail

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.modelairplanes.model.Payment
import com.modelairplanes.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.collections.ArrayList


class NewViewModel(val collectionReference: CollectionReference) : ViewModel() {

    private val _createDoc = MutableStateFlow(false)
    val createDoc: StateFlow<Boolean> = _createDoc

    private val _deleteDoc = MutableStateFlow(false)
    val deleteDoc: StateFlow<Boolean> = _deleteDoc

    fun saveData(user: User, dateLimit: Date, urlDocument: String) {
        collectionReference.document(user.uid!!).collection("docs").document().set(
            hashMapOf(
                "is_payment" to false,
                "limit_payment" to dateLimit,
                "url_document" to urlDocument
            )
        ).addOnSuccessListener {
            _createDoc.value = true
        }.addOnFailureListener {
            _createDoc.value = false
        }
    }

    fun deleteData(user: User, payment: Payment) {
        collectionReference.document(user.uid!!).collection("docs").document(payment.id!!).delete()
            .addOnSuccessListener {
                _deleteDoc.value = true
            }.addOnFailureListener {
                _deleteDoc.value = false
        }
    }
}