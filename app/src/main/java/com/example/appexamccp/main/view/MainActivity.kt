package com.example.appexamccp.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appexamccp.databinding.ActivityMainBinding
import com.example.appexamccp.main.adapter.UserAdapter
import com.example.appexamccp.main.repository.UserRepository
import com.example.appexamccp.main.viewModel.MainViewModel
import com.example.appexamccp.webService.WebServiceClient
import com.example.appexamccp.webService.responses.GetUsersResponse
import com.example.appexamccp.webService.responses.SingleUserResponse

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var users: GetUsersResponse
    private lateinit var singleUsers: ArrayList<SingleUserResponse>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        users = GetUsersResponse()
        singleUsers = arrayListOf()
        adapter = UserAdapter(singleUsers)

        viewModel = MainViewModel(
            UserRepository(
                WebServiceClient().getUserssApi()
            )
        )

        binding.apply {
            icRefresh.setOnClickListener(this@MainActivity)
            rvUsersList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsersList.adapter = adapter
        }

    }

    override fun onClick(v: View?) {
        binding.apply {
            when(v?.id){
                icRefresh.id -> {getAllUsers()}
            }
        }
    }

    private fun getAllUsers() {
        val users: GetUsersResponse= intent.extras?.get("Users") as GetUsersResponse
         users.forEach{
             viewModel.getSingleUser(it.login)
             viewModel.responseSingleUSer.observe(this){ singleUser ->
                 adapter.setList(singleUser)
             }
         }
    }
}