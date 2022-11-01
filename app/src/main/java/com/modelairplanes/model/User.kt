package com.modelairplanes.model

import java.io.Serializable

data class User(
    var email: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var uid: String? = null
): Serializable