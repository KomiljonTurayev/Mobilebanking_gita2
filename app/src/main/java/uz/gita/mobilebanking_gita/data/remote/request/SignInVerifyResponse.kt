package uz.gita.mobilebanking_gita.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignInVerifyResponse(
    @SerializedName("refresh-token")
    val refreshToken:String,
    @SerializedName("access-token")
    val accessToken:String
)