package co.cedesistemas.cakeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.cedesistemas.cakeapp.listener.ListenerProduct
import co.cedesistemas.cakeapp.models.ProductResponse

class DetailProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        //val response : ProductResponse = intent.extras!!.get("product") as ProductResponse
    }
}
