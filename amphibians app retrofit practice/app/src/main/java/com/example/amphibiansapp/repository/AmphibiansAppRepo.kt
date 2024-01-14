//package com.example.amphibiansapp.repository
//
//import com.example.amphibiansapp.data.DataStructure
//import com.example.amphibiansapp.network.AmphibiansAppApi
//import com.example.weatherforcast.model.Weather
//
//
//// declaring interface means we define methods . suppose we have different sources of data like we can retreive data from network,from local storage etc.
//// but they have same function name but different implementation. hence we declare interface
//
//interface AmphibiansAppRepo {
//    suspend fun getdata() :List<DataStructure>
//}
//
//class NetworkAmphibiansAppRepo() : AmphibiansAppRepo {
//    override suspend fun getdata(): Weather = AmphibiansAppApi.retrofitService.getFullData("lisbon")
//}