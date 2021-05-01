package co.cedesistemas.cakeapp.persistence.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.cedesistemas.cakeapp.models.ProductResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_product")
    val id: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "score")
    val score: Int,
    @ColumnInfo(name = "deliveryTime")
    val deliveryTime: Int,
    @ColumnInfo(name = "category")
    val category: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "localImage")
    val localImage: Bitmap
){
    fun toProductResponse() = ProductResponse(id, image, description, price, score, deliveryTime, category, name, localImage)
}
