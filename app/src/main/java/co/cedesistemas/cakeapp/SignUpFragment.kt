package co.cedesistemas.cakeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.cedesistemas.cakeapp.databinding.FragmentSignUpBinding
import co.cedesistemas.cakeapp.models.SignUpModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonsignUp.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val responseSignUp = withContext(Dispatchers.Default) {
                        val cakeRepository = CakeRepository()
                        cakeRepository.signUp(
                            SignUpModel(
                                binding.editTextName.text.toString(),
                                binding.editTextEmail.text.toString(),
                                binding.editTextPassword.text.toString()
                            )
                        )
                    }

                    Toast.makeText(this@SignUpFragment.context, responseSignUp.token, Toast.LENGTH_SHORT).show()

                } catch (exception: Exception) {
                    Toast.makeText(this@SignUpFragment.context, "${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
