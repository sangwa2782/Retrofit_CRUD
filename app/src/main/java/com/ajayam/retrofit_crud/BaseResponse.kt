package com.ajayam.retrofit_crud

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") var data: T? = null
)
