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
import com.ethanhua.skeleton.Skeleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        showSkeleton()
        localRepository = LocalCakeRepository(this@ShowProductsActivity)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val products = withContext(Dispatchers.Default) {
                    val cakeRepository = CakeRepository(this@ShowProductsActivity)
                    val internalProducts = cakeRepository.getAllProducts()
                    localRepository.insertProducts(internalProducts)
                    internalProducts
                }
                delay(3000)// solo para ambitos de que sepan que si se ve,
                // como el servicio puede ser rápido no se alcanza a ver la transición,
                // luego de que verifiquen borran o comentan esta línea
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

    private fun showSkeleton() {
        Skeleton.bind(binding.recyclerViewProducts)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1000)
            .count(6)
            .color(R.color.white_opaque)
            .load(R.layout.skeleton_item_product)
            .show()
    }
}
