package co.cedesistemas.cakeapp.listener

import co.cedesistemas.cakeapp.models.ProductResponse

interface ListenerProduct {

    fun onClickedProduct(product: ProductResponse)
}
