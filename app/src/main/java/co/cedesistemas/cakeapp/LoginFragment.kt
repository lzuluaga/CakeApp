package co.cedesistemas.cakeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.cedesistemas.cakeapp.databinding.FragmentLoginBinding
import co.cedesistemas.cakeapp.models.LoginModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class LoginFragment : Fragment() {
    
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { 
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val responseLogin = withContext(Dispatchers.Default){
                        val cakeRepository = CakeRepository()
                        cakeRepository.login(LoginModel(binding.editTextEmail.text.toString(),binding.editTextPassword.text.toString()))
                    }
                    Toast.makeText(this@LoginFragment.context, responseLogin.token, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginFragment.context, ShowProductsActivity::class.java))
                }catch (e: Exception){
                    Toast.makeText(this@LoginFragment.context, "${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    

 
}
