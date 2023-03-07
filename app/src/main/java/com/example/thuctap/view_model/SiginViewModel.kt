package com.example.thuctap.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthyt.util.Resource
import com.example.thuctap.compose.Login.SignInState
import com.example.thuctap.date.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SiginViewModel @Inject constructor(
    private val repository: AuthRepository,

) : ViewModel(){
    val _signInState = Channel<SignInState> ()
    val signInState =_signInState.receiveAsFlow()


    fun checkAuth():Boolean{
         return repository.checkAuth()
    }
    fun loginUser( email:String, password:String)=viewModelScope.launch {
        repository.loginUser(email, password).collect{result->
            when(result){
                is Resource.Success->{
                    _signInState.send(SignInState(isSuccess = "Sign In Success "))
                }
                is Resource.Loading->{
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error->{
                    _signInState.send(SignInState(isError = result.message))

                }
            }
        }

        }
    }