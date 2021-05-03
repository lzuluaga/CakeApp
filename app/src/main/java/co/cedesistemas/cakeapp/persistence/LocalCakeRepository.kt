package co.cedesistemas.cakeapp.persistence

import android.content.Context
import co.cedesistemas.cakeapp.models.ProductResponse
import co.cedesistemas.cakeapp.persistence.dao.ProductDao
import java.lang.Exception

class LocalCakeRepository(context: Context) {


    private lateinit var productDao: ProductDao

    init {
        CakeBD.getInstance(context)?.let {cakeBD ->
            productDao = cakeBD.productDao()
        }
    }


    fun insertProducts(listProducts: List<ProductResponse>){
        try {
            productDao.insertProducts(listProducts.map {
                it.toProductEntity()
            })
        }catch (e: Exception){
            throw e
        }

    }

    fun getAllProducts(): List<ProductResponse> {
        try {
            return productDao.getAllProducts().map {
                it.toProductResponse()
            }
        }catch (e: Exception){
            throw e
        }
    }
}
