package com.example.thuctap.date

import com.example.firebaseauthyt.util.Resource
import com.example.thuctap.date.model.Profile
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    fun loginUser(email:String,password:String): Flow<Resource<AuthResult>>
    fun RegisterUser (email:String,password:String): Flow<Resource<AuthResult>>


    fun checkAuth():Boolean
}