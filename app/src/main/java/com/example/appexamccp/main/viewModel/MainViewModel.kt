package com.example.appexamccp.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appexamccp.common.util.RequestState
import com.example.appexamccp.main.datasource.UserDataSource
import com.example.appexamccp.main.repository.UserRepository
import com.example.appexamccp.webService.responses.GetUsersResponse
import com.example.appexamccp.webService.responses.SingleUserResponse

class MainViewModel( private val repository: UserRepository): ViewModel() {

    lateinit var responseUsers: MutableLiveData<GetUsersResponse>
    lateinit var responseSingleUSer: MutableLiveData<SingleUserResponse>

    var userDatSource: UserDataSource = repository.requetsGetUsersDataSource()
    var responseState: MutableLiveData<RequestState> = userDatSource.responseState

    fun getUsers(){
        responseUsers = userDatSource.responseUsers
        userDatSource.requestAllUsers()
    }

    fun getSingleUser(idUser: String){
        responseSingleUSer = userDatSource.responseSingleUser
        userDatSource.requestSingleUSer(idUser)
    }
}