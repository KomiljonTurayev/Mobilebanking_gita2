package uz.gita.mobilebanking_gita.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val phone: String,
    val password: String,
    @SerializedName("first-name")
    val firstName: String,
    @SerializedName("last-name")
    val lastName: String,
    @SerializedName("born-date")
    val bornDate: String,
    val gender: String,
)

/*{
    "phone": "+998993946280",
    "password": "qwerty",
    "first-name": "Muhammadali",
    "last-name": "Rahimberganov",
    "born-date": "969822000000",
    "gender": "0"
}*/