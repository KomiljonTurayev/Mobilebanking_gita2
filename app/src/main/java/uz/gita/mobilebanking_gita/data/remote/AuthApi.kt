package uz.gita.mobilebanking_gita.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.mobilebanking_gita.data.remote.request.*

interface AuthApi {
    @POST("auth/sign-in")
    suspend fun signIn(@Body data: SignInRequest): Response<SignInResponse>

    @POST("auth/sign-up")
    suspend fun signUp(@Body data: SignUpRequest): Response<SignUpResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(@Body data: SignUpVerifyRequest): Response<SignUpVerifyResponse>

    @POST("auth/sign-in/verify")
    suspend fun signInVerify(@Body data: SignInVerifyRequest):Response<SignInVerifyResponse>
}