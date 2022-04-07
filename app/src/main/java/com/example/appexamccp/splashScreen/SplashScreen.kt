package com.example.appexamccp.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appexamccp.R
import com.example.appexamccp.main.repository.UserRepository
import com.example.appexamccp.main.view.MainActivity
import com.example.appexamccp.main.viewModel.MainViewModel
import com.example.appexamccp.webService.WebServiceClient

class SplashScreen : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel = MainViewModel(
            UserRepository(
                WebServiceClient().getUserssApi()
            )
        )

        viewModel.getUsers()
        viewModel.responseUsers.observe(this){ userResponse ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}