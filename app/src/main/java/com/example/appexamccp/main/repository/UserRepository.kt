package com.example.appexamccp.main.repository

import com.example.appexamccp.main.datasource.UserDataSource
import com.example.appexamccp.webService.api.UserApi

class UserRepository(private val userApi: UserApi) {

    fun requetsGetUsersDataSource(): UserDataSource {
        return UserDataSource(
            userApi
        )
    }
}