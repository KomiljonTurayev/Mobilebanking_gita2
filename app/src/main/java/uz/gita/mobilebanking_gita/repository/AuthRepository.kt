package uz.gita.mobilebanking_gita.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebanking_gita.data.local.LocalStorage
import uz.gita.mobilebanking_gita.data.remote.AuthApi
import uz.gita.mobilebanking_gita.data.remote.request.SignInRequest
import uz.gita.mobilebanking_gita.data.remote.request.SignInVerifyRequest
import uz.gita.mobilebanking_gita.data.remote.request.SignUpRequest
import uz.gita.mobilebanking_gita.data.remote.request.SignUpVerifyRequest
import uz.gita.mobilebanking_gita.utils.ResultData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi,
) {
    fun isFirstLaunch(): Boolean = localStorage.isFirstLaunch

    fun isSinged(): Boolean {
        return false
    }


    fun disableFirstLaunch() {
        localStorage.isFirstLaunch = false
    }

    fun login(phone: String, password: String): Flow<ResultData<String>> = flow {
        val request = SignInRequest(phone, password)
        val response = authApi.signIn(request)
        if (response.isSuccessful) {
            val responseBody = response.body()
            val token: String = responseBody?.token.toString()
            emit(ResultData.Success(token))
        } else {
            emit(ResultData.Error("Xatolik sodir bo'ldi"))
        }
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(ResultData.Error("Xatolik sodir bo'ldi")) }

    fun signup(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        phoneNumber: String,
        password: String,
        gender: String,
    ): Flow<ResultData<String>> = flow {
        val request = SignUpRequest(phoneNumber, password, firstName, lastName, dateOfBirth, gender)
        val response = authApi.signUp(request)
        if (response.isSuccessful) {
            val responseBody = response.body()
            val token: String = responseBody?.token.toString()
            emit(ResultData.Success(token))
        } else {
            emit(ResultData.Error("Xatolik sodir bo'ldi"))
        }
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(ResultData.Error("Xatolik sodir bo'ldi")) }

    fun signUpVerify(signUpVerifyRequest: SignUpVerifyRequest): Flow<ResultData<String>> = flow {
        val request = authApi.signUpVerify(signUpVerifyRequest)

        if (request.isSuccessful) {
            localStorage.token = request.body()!!.accessToken
            emit(ResultData.Success("Success"))
        } else {
            emit(ResultData.Error("Xatolik sodir bo'ldi"))
        }
    }

    fun signInVerify(signInVerifyRequest: SignInVerifyRequest): Flow<ResultData<String>> = flow {
        val request = authApi.signInVerify(signInVerifyRequest)

        if (request.isSuccessful) {
            localStorage.token = request.body()!!.accessToken
            emit(ResultData.Success("Success"))
        } else {
            emit(ResultData.Error("Xatolik sodir bo'ldi"))
        }
    }
}