package com.ajayam.retrofit_crud

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        findViewById<Button>(R.id.btnGet).setOnClickListener{
            getUserByID()
        }
        findViewById<Button>(R.id.btnUpdate).setOnClickListener{
            UpdateUser()
        }
        findViewById<Button>(R.id.btnDelete).setOnClickListener{
            DeleteUser()
        }

        findViewById<Button>(R.id.btnPost).setOnClickListener{
            CreateUser()
        }


    }

    private fun CreateUser() {
        lifecycleScope.launch {
            showLoading("Creating, please wait...")
            var body = JsonObject().apply {
                addProperty("name", "Ajayam Organisation" )
                addProperty("job", "Android Developer")
            }
            val result = apiService.createUser(body)
            if (result.isSuccessful) {
                Log.e("00000", "CreateUser sucess: ${result.body()}")
            } else {
                Log.e("00000", "CreateUser failed: ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun DeleteUser() {
        lifecycleScope.launch {
            showLoading("Deleting, please wait...")
            val result = apiService.deleteUser("2")
            if (result.isSuccessful) {
                Log.e("00000", "DeleteUser sucess ${result.body()} ")
            } else {
                Log.e("00000", "DeleteUser field ${result.message()}")
            }
            progressDialog?.dismiss()
        }
    }

    private fun UpdateUser() {
        lifecycleScope.launch {
            showLoading("Updating user...")
            val body = JsonObject().apply {
                addProperty("name", "Ajayam Organisation")
                addProperty("job", "Android Developer")
            }
            val result = apiService.updateUser("2", body)
            if (result.isSuccessful) {
                Log.e("00000", "UpdateUser Success: ${result.body()}")
            } else {
                Log.e("00000", "UpdateUser failed: ${result.message()}", )
            }
            progressDialog?.dismiss()
        }
    }

    private fun getUserByID() {
        lifecycleScope.launch {
            showLoading("Getting, please wait...")
            val result = apiService.getUserById("2")
            if(result.isSuccessful) {
                //get request successfull
                Log.e("00000", "getUserByID sucess: ${result.body()?.data} ")
            } else {
                //get request failed
                Log.e("00000", "getUserByID failed: ${result.message()} ", )
            }
            progressDialog?.dismiss()
        }
    }

    private fun showLoading(msg: String) {
        progressDialog = ProgressDialog.show(this, null, msg, true)
    }


}