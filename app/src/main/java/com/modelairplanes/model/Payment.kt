package com.modelairplanes.model

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.Date


data class Payment(
    var is_payment: Boolean? = false,
    var limit_payment: Date? = null,
    var url_document: String? = null,
    var id: String? = null,
) : Serializable {
    @get:ServerTimestamp
    var datePayment: Date?
        get() = limit_payment
        set(timestamp) {
            limit_payment = timestamp
        }
}