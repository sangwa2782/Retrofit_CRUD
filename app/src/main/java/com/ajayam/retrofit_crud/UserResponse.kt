package com.ajayam.retrofit_crud

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") var id:Int? = 0,
    @SerializedName("email") var email:String? = "",
    @SerializedName("first_name") var firstName:String? = "",
    @SerializedName("last_name") var lastName:String? = "",
    @SerializedName("avatar") var avatar:String? = ""
)
