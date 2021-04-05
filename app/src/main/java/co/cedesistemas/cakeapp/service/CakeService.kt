package co.cedesistemas.cakeapp.service

import co.cedesistemas.cakeapp.models.LoginModel
import co.cedesistemas.cakeapp.models.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CakeService {

    @POST("users/login")
    suspend fun login(@Body loginModel: LoginModel) : Response<LoginResponseModel>
}
