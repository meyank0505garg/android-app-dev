package com.example.mykotlin

fun main() {

    val listOfItems= listOf<String>("Mayank", "garg","ok","complete")

    val finder = Finder(listOfItems)
    finder.find_item("ok"){
        println("found ${it}")

    }

    finder.find_item(""){
        println("found ${it}")

    }

    val listOfInt = listOf<Int>(23,34,56,234)
    val new_finder = Finder(listOfInt);

    new_finder.find_item(23){
        println("found ${it}")
    }

    new_finder.find_item(24){
        println("found ${it}")
    }

}

//class Finder(private val list:List<String>){
//
//    fun find_item(element: String,foundItem:(element:String?)->Unit){
//        val itemFoundList = list.filter {
//            it==element
//        }
//
//        if(itemFoundList.isNullOrEmpty()){
//            foundItem(null)
//        }else{
//            foundItem(itemFoundList.first())
//        }
//
//
//
//    }
//
//
//}

class Finder<T>(private val list:List<T>){

    fun find_item(element: T,foundItem:(element:T?)->Unit){
        val itemFoundList = list.filter {
            it==element
        }

        if(itemFoundList.isNullOrEmpty()){
            foundItem(null)
        }else{
            foundItem(itemFoundList.first())
        }



    }


}

