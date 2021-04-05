package co.cedesistemas.cakeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.cedesistemas.cakeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding : ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabsAdapter = TabsAdapter(supportFragmentManager)
        tabsAdapter.addFragments(LoginFragment(), "Login")
        tabsAdapter.addFragments(SingUpFragment(), "SingUp")
        binding.viewPager.adapter = tabsAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        
    }
}
