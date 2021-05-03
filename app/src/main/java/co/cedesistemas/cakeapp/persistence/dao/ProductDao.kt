package co.cedesistemas.cakeapp.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.cedesistemas.cakeapp.persistence.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM product")
    fun getAllProducts():List<ProductEntity>

    @Query("SELECT * FROM product WHERE category = :id ")
    fun getProductsById(id: Int):List<ProductEntity>


}
