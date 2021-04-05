package co.cedesistemas.cakeapp

import co.cedesistemas.cakeapp.models.LoginModel
import co.cedesistemas.cakeapp.models.LoginResponseModel
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
}