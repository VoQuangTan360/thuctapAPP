package com.example.thuctap.view_model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthyt.util.Resource
import com.example.thuctap.compose.Login.SignInState
import com.example.thuctap.date.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel(){
    val _signUpState = Channel<SignInState> ()
    val signUpState =_signUpState.receiveAsFlow()

    fun RegisterUser( email:String, password:String)=viewModelScope.launch {
        repository.RegisterUser(email, password).collect{result->
            when(result){
                is Resource.Success->{
                    Log.d(TAG,"thanhcong    ")
                    _signUpState.send(SignInState(isSuccess = "Sign In Success "))
                }
                is Resource.Loading->{
                    _signUpState.send(SignInState(isLoading = true))
                    Log.d(TAG,"loading")
                }
                is Resource.Error->{
                    _signUpState.send(SignInState(isError = result.message))
                    Log.d(TAG,"loi "+result.message.toString())

                }
            }
        }

    }
}