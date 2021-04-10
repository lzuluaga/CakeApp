package co.cedesistemas.cakeapp.models

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("name")
    val fullName: String,
    val email: String,
    val password: String
)
