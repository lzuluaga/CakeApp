package co.cedesistemas.cakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import co.cedesistemas.cakeapp.databinding.ActivityShowProductsBinding
import co.cedesistemas.cakeapp.listener.ListenerProduct
import co.cedesistemas.cakeapp.models.ProductResponse
import co.cedesistemas.cakeapp.persistence.LocalCakeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.ConnectException

class ShowProductsActivity : AppCompatActivity(), ListenerProduct {

    private lateinit var binding: ActivityShowProductsBinding
    private lateinit var localRepository: LocalCakeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerViewProducts.layoutManager = GridLayoutManager(this@ShowProductsActivity, 2)
        localRepository = LocalCakeRepository(this@ShowProductsActivity)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val products = withContext(Dispatchers.Default) {
                    val cakeRepository = CakeRepository(this@ShowProductsActivity)
                    val internalProducts = cakeRepository.getAllProducts()
                    localRepository.insertProducts(internalProducts)
                    internalProducts
                }

                binding.recyclerViewProducts.adapter = ProductsAdapter(products, this@ShowProductsActivity)

            } catch (e: Exception) {
                if (e is ConnectException) {
                    val productsResponse = withContext(Dispatchers.Default) {
                        localRepository.getAllProducts()
                    }
                    binding.recyclerViewProducts.adapter = ProductsAdapter(productsResponse, this@ShowProductsActivity)
                } else {
                    Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClickedProduct(product: ProductResponse) {
        startActivity(Intent(this, DetailProductActivity::class.java).apply {
            //putExtra("product", product)
        })
    }
}
