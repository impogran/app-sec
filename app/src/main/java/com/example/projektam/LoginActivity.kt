package com.example.projektam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projektam.DB.DBHelperR

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.login)
        val buttonRegister = findViewById<Button>(R.id.register)
        val textLogin = findViewById<EditText>(R.id.nickLogin)
        val textPass = findViewById<EditText>(R.id.passLogin)
        val textRegLogin = findViewById<EditText>(R.id.nickReg)
        val textRegPass = findViewById<EditText>(R.id.passReg)
        val myDB = DBHelperR(this);
        //myDB.XD()
        buttonLogin.setOnClickListener(){
            val login = textLogin.text.toString()
            val pass = textPass.text.toString()

            if (login == "" || pass == ""){
                Toast.makeText(this, "Wprowadź tekst", Toast.LENGTH_SHORT).show()
            }
            else if(myDB.loginCheck(login,pass)){
                //remembering logged in user's login
                val sharedScore = this.getSharedPreferences("com.example.projektam",0)
                val edit = sharedScore.edit()
                edit.putString("logged", login)
                edit.apply()
                val thread = Thread(){
                    runOnUiThread(){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        this.onPause()
                    }
                }
                thread.start()
            }
            else{
                Toast.makeText(this, "Błędny nick lub hasło", Toast.LENGTH_SHORT).show()
            }
        }

        buttonRegister.setOnClickListener(){
            val login = textRegLogin.text.toString()
            val pass = textRegPass.text.toString()
            if (login == "" || pass == ""){
                Toast.makeText(this, "Wprowadź tekst", Toast.LENGTH_SHORT).show()
            }
            else if(myDB.nickCheck(login) == false){
                if(myDB.addUser(login,pass)){
                    myDB.newGame(login)
                    Toast.makeText(this, "Zarejestrowano!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Coś poszło nie tak", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Taki użytkownik już istnieje", Toast.LENGTH_SHORT).show()
            }
        }
    }
}