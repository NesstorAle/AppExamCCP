package com.example.appexamccp.webService.api

import com.example.appexamccp.common.constants.Constants
import com.example.appexamccp.webService.responses.GetUsersResponse
import com.example.appexamccp.webService.responses.SingleUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET(Constants.END_POINT_GET_ALL_USERS)
    fun getAllUsers(): Call<GetUsersResponse>

    @GET("${Constants.END_POINT_GET_SINGLE_USER}{userId}")
    fun getSingleUSer(@Path("userId") idUser: String): Call<SingleUserResponse>
}