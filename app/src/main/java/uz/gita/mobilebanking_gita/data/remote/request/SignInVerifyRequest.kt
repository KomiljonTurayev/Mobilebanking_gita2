package uz.gita.mobilebanking_gita.data.remote.request

data class SignInVerifyRequest(val token: String, val code: String)