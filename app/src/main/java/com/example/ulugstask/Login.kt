package com.example.ulugstask

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.login_layout.*

class Login: AppCompatActivity() {
    private lateinit var jsonApi: MyApi
    private var compositeDisposable = CompositeDisposable()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(MyApi::class.java)

        search_button.setOnClickListener {
            if( validateLogin(usernamelogin.text.toString())){
                doLogin(usernamelogin.text.toString())
            }
        }
    }

    private fun validateLogin(username:String):Boolean
    {
        if (username.trim().isEmpty()){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun doLogin(login: String)
    {
             compositeDisposable.add(jsonApi.login(login)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe(this::handleResponse, this::handleError))
    }
    private fun handleResponse(itemList: User)
    {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username",usernamelogin.text.toString())
        intent.putExtra("avatar", itemList.avatar_url)
        intent.putExtra("name", itemList.name)
        startActivity(intent)
    }
    private fun handleError(t: Throwable)
     {
         Toast.makeText(this, "Clicked !$t", Toast.LENGTH_SHORT).show()
     }
}


