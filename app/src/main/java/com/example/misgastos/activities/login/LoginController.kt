package com.example.misgastos.activities.login

class LoginController {

    fun login(userName: String, password: String): Boolean {
        return userName == "admin" && password == "nimda"
    }
}
