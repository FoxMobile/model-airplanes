package com.modelairplanes.ui.home

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date


data class Payment(
    var is_payment: Boolean? = false,
    var limit_payment: Date? = null,
    var url_document: String? = null,
) {
    @get:ServerTimestamp
    var datePayment: Date?
        get() = limit_payment
        set(timestamp) {
            limit_payment = timestamp
        }
}