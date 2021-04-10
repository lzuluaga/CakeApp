package co.cedesistemas.cakeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.cedesistemas.cakeapp.databinding.ActivityShowProductsBinding

class ShowProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
