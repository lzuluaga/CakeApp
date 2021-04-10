package co.cedesistemas.cakeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import co.cedesistemas.cakeapp.databinding.ActivityShowProductsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ShowProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CoroutineScope(Dispatchers.Main).launch {
            try {
                val products = withContext(Dispatchers.Default){
                    val cakeRepository = CakeRepository()
                    cakeRepository.getAllProducts()
                }

                binding.recyclerViewProducts.layoutManager = GridLayoutManager(this@ShowProductsActivity, 2)
                binding.recyclerViewProducts.adapter = ProductsAdapter(products)

            }catch (e: Exception){
                Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
