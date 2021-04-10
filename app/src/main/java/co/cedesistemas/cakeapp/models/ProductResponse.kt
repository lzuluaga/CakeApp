package co.cedesistemas.cakeapp.models

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("_id")
    val id: String,
    val image: String,
    val description: String,
    val price: Int,
    val score: Int,
    @SerializedName("delivery_time")
    val deliveryTime: Int,
    val category: Int,
    val name: String
)
