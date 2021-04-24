package co.cedesistemas.cakeapp

import android.content.Context
import co.cedesistemas.cakeapp.models.LoginModel
import co.cedesistemas.cakeapp.models.LoginResponseModel
import co.cedesistemas.cakeapp.models.ProductResponse
import co.cedesistemas.cakeapp.models.SignUpModel
import co.cedesistemas.cakeapp.service.CakeService
import co.cedesistemas.cakeapp.service.ServiceFactory

class CakeRepository(val context: Context) {
    
    private var cakeService: CakeService
    
    init {
        val serviceFactory = ServiceFactory()
        cakeService = serviceFactory.getInstanceCakeService(context)
    }

    suspend fun login(loginModel: LoginModel): LoginResponseModel {
        val response = cakeService.login(loginModel)
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }

    suspend fun signUp(signUpModel: SignUpModel): LoginResponseModel {
        val response = cakeService.signUp(signUpModel)
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }

    suspend fun getAllProducts(): List<ProductResponse> {
        val response = cakeService.getAllProducts()
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }

    suspend fun autoLogin(token: String) : Boolean {
        val response = cakeService.autoLogin("Bearer $token")
        return response.isSuccessful
    }


    suspend fun getDrinks(category: Int): List<ProductResponse> {
        val response = cakeService.getDrinks(category)
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }
}
