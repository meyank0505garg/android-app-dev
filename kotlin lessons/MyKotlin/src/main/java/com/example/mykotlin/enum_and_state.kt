package com.example.mykotlin


fun main() {
//    val input = Result.SUCCESS
//    getResult(input)

    Repository.startFetch()
    getResult(Repository.getCurrentState())
    Repository.finishedFetch()
    getResult(Repository.getCurrentState())

}



fun getResult(result: Result){

    when (result){
        is ERROR -> {
            println(result.error.toString())
        }

        is SUCCESS -> {
            println(result.dataFetched?: "Ensure you start the fetch function first")

        }

        is Loading -> {
            println("Loading ...")
        }

        is NotLoading -> {
            println("Idle")
        }
        else -> println("NA")
    }
}

object Repository{
    private var loadState:Result = NotLoading
    private var dataFetched : String? = null

    fun startFetch() {
        loadState = Loading
        dataFetched = "data"

    }

    fun finishedFetch() {
        loadState = SUCCESS(dataFetched)
//        dataFetched = null
    }

    fun error() {

        loadState = ERROR(Exception("Exception"))


    }

    fun getCurrentState() : Result {
        return  loadState
    }


}

//
//enum class Result{
//    SUCCESS,
//    FAILURE,
//    ERROR,
//    IDLE,
//    LOAD
//
//
//}

abstract class Result

data class SUCCESS(var dataFetched : String?) : Result()
data class ERROR(var error : Exception) : Result()

object NotLoading:Result()
object Loading : Result()

