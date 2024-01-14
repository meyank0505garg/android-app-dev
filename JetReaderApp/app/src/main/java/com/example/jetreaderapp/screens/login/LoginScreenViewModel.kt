package com.example.jetreaderapp.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreaderapp.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {

//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth : FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading : LiveData<Boolean> = _loading


    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) {

        viewModelScope.launch {
            if(_loading.value == false){
                _loading.value = true
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {task->
                        if(task.isSuccessful){
                            val displayName = task.result.user?.email?.split('@')?.get(0)
                            createUser(displayName)
                            home()
                        }else{
                            Log.d("FB", "createUserWithEmailAndPassword: Failure ${task.result.toString()}")
                        }
                        _loading.value = false

                    }
            }

        }




    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(userId = userId.toString(), displayName = displayName.toString(), avatarUrl = "", quote = "Life is great", profession = "Android Dev",id = null).toMap()


        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }

    fun signInWithEmailAndPassword(email : String , password : String, home :() -> Unit) =

//        if error the try to remove {} of function and add = before viewmodelscope direcltly


        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {task->
                        if(task.isSuccessful){
                            Log.d("FB", "signInWithEmailAndPassword: success !!  ${task.result.toString()}")
//                            TODO("Take them home")
                            home()
                        }else{
                            Log.d("FB", "signInWithEmailAndPassword: Failure ${task.result.toString()}")
                        }

                    }

            }catch (ex:Exception){
                Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")

            }

        }




}