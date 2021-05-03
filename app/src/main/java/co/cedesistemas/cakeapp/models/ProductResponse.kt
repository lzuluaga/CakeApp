package co.cedesistemas.cakeapp.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import co.cedesistemas.cakeapp.persistence.entities.ProductEntity
import co.cedesistemas.cakeapp.utils.URL
import com.google.gson.annotations.SerializedName
import java.net.URL

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
    val name: String,
    val bitmap: Bitmap? = null
){
    fun toProductEntity() = ProductEntity(id, image, description, price, score, deliveryTime, category, name, urlToBitmap("$URL${image}"))

    private fun urlToBitmap(url: String): Bitmap {
        val openStream = URL(url).openStream()
        return BitmapFactory.decodeStream(openStream)
    }
}
