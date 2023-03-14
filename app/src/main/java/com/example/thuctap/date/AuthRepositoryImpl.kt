package com.example.thuctap.date

import com.example.firebaseauthyt.util.Resource
import com.example.thuctap.date.model.Profile
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    val database: FirebaseFirestore,
    val storageReference: StorageReference
) : AuthRepository {

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow{
            emit(Resource.Loading())
            val result= firebaseAuth.signInWithEmailAndPassword(email,password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun RegisterUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow{
            emit(Resource.Loading())
            val result= firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }



    override fun checkAuth(): Boolean {
        return firebaseAuth.currentUser!=null
    }
}