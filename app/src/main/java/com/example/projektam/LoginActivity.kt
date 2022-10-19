package com.example.projektam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.login)
        val textLogin = findViewById<EditText>(R.id.nickLogin)
        val textPass = findViewById<EditText>(R.id.passLogin)
        buttonLogin.setOnClickListener(){
            val login = textLogin.text.toString()
            val pass = textPass.text.toString()

            if (login == "" || pass == ""){
                Toast.makeText(this, "Wprowadź tekst", Toast.LENGTH_SHORT).show()
            }
            else {
                val LoginPassword = LoginPassword()
                LoginPassword.login = login
                LoginPassword.password = pass
                val retroInterface = RetrofitClient.getRetroInterface("http://192.168.23.23")
                val loginCall = retroInterface.login(LoginPassword)

                loginCall.enqueue(object: Callback<JWT> {
                    override fun onResponse(call: Call<JWT>, response: Response<JWT>) {
                        if (response.code() == 200) {
                            Toast.makeText(applicationContext, response.body()?.JWT ?: "sory nie ma tokenu", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<JWT>, t: Throwable) {
                        Toast.makeText(applicationContext, "jakiś error", Toast.LENGTH_LONG).show()
                    }
                })
                /*
                val thread = Thread(){
                    runOnUiThread(){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        this.onPause()
                    }
                }
                thread.start()
                */
            }
        }
    }
}