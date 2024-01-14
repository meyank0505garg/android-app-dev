package com.example.amphibiansapp.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansapp.network.AmphibiansAppApi
import com.example.weatherforcast.model.Weather
import kotlinx.coroutines.launch


sealed interface AppState{
    data class Success(
        val data :Weather


    ) : AppState

    object Error : AppState
    object Loading : AppState


}
class AppViewModel : ViewModel() {

    var appState : AppState by mutableStateOf(AppState.Loading)
        private set

    fun getdata () {
        viewModelScope.launch{
            appState = AppState.Loading

            appState = try{
                val listResult = AmphibiansAppApi.retrofitService.getFullData("Lisbon")
                Log.d("TAG", "getdata: is Success")
                AppState.Success(
                    data = listResult
                )




            }catch (e : Exception) {

                Log.d("TAG", "getdata: $e")
                AppState.Error

            }




        }
    }

    init {
        getdata()
    }


}