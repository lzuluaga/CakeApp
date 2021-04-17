package co.cedesistemas.cakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.cedesistemas.cakeapp.utils.MySharedPreferences
import co.cedesistemas.cakeapp.utils.TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    private lateinit var myPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        myPreferences = MySharedPreferences(this)
        validateToken()
    }

    private fun validateToken() {
        val token = myPreferences.getString(TOKEN)
        if (token != null){
            autoLogin(token)
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun autoLogin(token: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.Default){
                    val cakeRepository = CakeRepository()
                    cakeRepository.autoLogin(token)
                }
                if (response){
                    startActivity(Intent(this@SplashActivity, ShowProductsActivity::class.java))
                }else{
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }

            }catch (e: Exception){
                Toast.makeText(this@SplashActivity, "${e.message}", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }
}
