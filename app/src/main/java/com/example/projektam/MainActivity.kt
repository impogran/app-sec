package com.example.projektam
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ShareActionProvider
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    lateinit var  shareActionProvider: ShareActionProvider

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTV = findViewById<TextView>(R.id.mainTV)
        val mainET = findViewById<EditText>(R.id.mainET)
        val mainBTN = findViewById<Button>(R.id.mainBTN)
        val token = "Bearer " + intent.getStringExtra("token")

        val retroInterface = RetrofitClient.getRetroInterface(Constants.URL)

        mainBTN.setOnClickListener() {
            val endpoint = mainET.text.toString()



            if (endpoint.equals("/api/users")) {
                val allUserCall = retroInterface.getAllUsers(token)
                allUserCall.enqueue(object: Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        if (response.code() == 200) {
                            mainTV.setText(response.body().toString())
                        }
                    }
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            } else if (endpoint.contains("/api/users/")) {
                val username = endpoint.split("users/")[1]
                val oneUserCall = retroInterface.getUser(username, token)
                oneUserCall.enqueue(object: Callback<BasicResponse> {
                    override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                        if (response.code() == 200) {
                            mainTV.setText(response.body()?.message ?: "no message")
                        }
                    }
                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            } else if (endpoint.equals("/api/hidden")) {
                val hiddenCall = retroInterface.getHidden(token)
                hiddenCall.enqueue(object: Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.code() == 200) {
                            mainTV.setText(response.body()?.message ?: "no message")
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            } else if (endpoint.equals("/api/allEndpoints")) {
                val allEndpointsCall = retroInterface.getAllEndpoints(token)
                allEndpointsCall.enqueue(object : Callback<List<BasicResponse>> {
                    override fun onResponse(
                        call: Call<List<BasicResponse>>,
                        response: Response<List<BasicResponse>>
                    ) {
                        if (response.code() == 200) {
                            mainTV.setText(response.body()?.toString()?: "no message")
                        }
                    }

                    override fun onFailure(call: Call<List<BasicResponse>>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
            else {
                mainTV.setText("Taki endpoint nie istnieje")
            }
        }
    }
}

