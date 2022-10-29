package com.modelairplanes.ui.home

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query


class HomeViewModel(private val docReference: CollectionReference) : ViewModel() {

    var query: Query = docReference.limit(10).orderBy("data", Query.Direction.ASCENDING)
}