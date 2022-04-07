package com.example.appexamccp.main.datasource

import androidx.lifecycle.MutableLiveData
import com.example.appexamccp.common.util.RequestState
import com.example.appexamccp.webService.api.UserApi
import com.example.appexamccp.webService.responses.GetUsersResponse
import com.example.appexamccp.webService.responses.SingleUserResponse
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource(private val userApi: UserApi) {
    var responseState = MutableLiveData<RequestState>()
    var responseUsers = MutableLiveData<GetUsersResponse>()
    var responseSingleUser = MutableLiveData<SingleUserResponse>()

    fun requestAllUsers(){
        responseState.value = RequestState(RequestState.REQ_IN_PROGRESS)

        userApi.getAllUsers().enqueue(object: Callback<GetUsersResponse> {
            override fun onResponse(
                call: Call<GetUsersResponse>,
                response: Response<GetUsersResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        responseState.value = RequestState(RequestState.REQ_OK)
                        responseUsers.postValue(response.body())
                    }

                    else -> {
                        responseState.value = RequestState(RequestState.REQ_BAD)
                        try{
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            val gson = Gson()
                            responseUsers.postValue(
                                gson.fromJson(
                                    jObjError.toString(),
                                    GetUsersResponse::class.java
                                )
                            )
                        }catch (e : Exception){
                            responseUsers.postValue(GetUsersResponse())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GetUsersResponse>, t: Throwable) {
                responseState.value = RequestState(RequestState.REQ_BAD)
                responseUsers.postValue(GetUsersResponse())
            }

        })
    }

    fun requestSingleUSer(userId: String){
        responseState.value = RequestState(RequestState.REQ_IN_PROGRESS)

        userApi.getSingleUSer(userId).enqueue(object: Callback<SingleUserResponse>{
            override fun onResponse(
                call: Call<SingleUserResponse>,
                response: Response<SingleUserResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        responseState.value = RequestState(RequestState.REQ_OK)
                        responseSingleUser.postValue(response.body())
                    }

                    else -> {
                        responseState.value = RequestState(RequestState.REQ_BAD)
                        try{
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            val gson = Gson()
                            responseSingleUser.postValue(
                                gson.fromJson(
                                    jObjError.toString(),
                                    SingleUserResponse::class.java
                                )
                            )
                        }catch (e : Exception){
                        }
                    }
                }
            }

            override fun onFailure(call: Call<SingleUserResponse>, t: Throwable) {
                responseState.value = RequestState(RequestState.REQ_BAD)
            }

        })
    }
}