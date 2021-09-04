package com.vmmarinc.sweetanikca2.data.repositories

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.sql.CommonDataSource

class UserRepository(private val dataSource: FirebaseAuth, private val dataSourceStorage:StorageReference) {
    suspend fun isLoggedIn(): FirebaseUser?{
        return dataSource.currentUser
    }

    suspend fun logOut(): FirebaseUser?{
        dataSource.signOut()
        return null
    }

    suspend fun signup(email: String, name: String, pass: String) : FirebaseUser{
        try {
            Log.d("CREDENTIALS signup", pass)
            dataSource.createUserWithEmailAndPassword(email, pass).await()
            val user = dataSource.currentUser
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user!!.updateProfile(profileUpdate).await()
            return user
        }catch (e: FirebaseAuthUserCollisionException){
            throw Error("Correo en uso")
        }
    }

    suspend fun login(email: String, pass: String):FirebaseUser?{
        try {
            Log.d("CREDENTIALS login", pass)
            dataSource.signInWithEmailAndPassword(email, pass).await()
            return dataSource.currentUser
        }catch (e: FirebaseAuthInvalidUserException){
            throw Error("Usuario invalido")
        }catch (e: FirebaseAuthInvalidCredentialsException){
            throw Error("Credenciales invalidas")
        }
    }

    suspend fun uploadImage(bitmap: Bitmap): FirebaseUser?{
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val profileRef = dataSourceStorage.child("${dataSource.currentUser!!.uid}.jpg")
        val uploadTask = profileRef.putBytes(data).await()
        val uri = profileRef.downloadUrl.await()
        val profileUpdate = userProfileChangeRequest {
            photoUri = uri
        }
        val user = dataSource.currentUser
        user!!.updateProfile(profileUpdate).await()
        return user
    }
}