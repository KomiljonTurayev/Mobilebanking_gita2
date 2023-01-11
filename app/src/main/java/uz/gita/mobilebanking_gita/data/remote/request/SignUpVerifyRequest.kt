package uz.gita.mobilebanking_gita.data.remote.request

data class SignUpVerifyRequest(val token: String, val code: String)