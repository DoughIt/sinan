package com.doughit.sinan.network

import com.doughit.sinan.model.CommonResponse
import com.doughit.sinan.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/ums/info")
    fun getUserInfo(): Call<CommonResponse<User>>
}