package co.cedesistemas.cakeapp

import co.cedesistemas.cakeapp.models.LoginModel
import co.cedesistemas.cakeapp.models.LoginResponseModel
import co.cedesistemas.cakeapp.models.ProductResponse
import co.cedesistemas.cakeapp.models.SignUpModel
import co.cedesistemas.cakeapp.service.CakeService
import co.cedesistemas.cakeapp.service.ServiceFactory

class CakeRepository {
    
    private var cakeService: CakeService
    
    init {
        val serviceFactory = ServiceFactory()
        cakeService = serviceFactory.getInstanceCakeService()
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
}
