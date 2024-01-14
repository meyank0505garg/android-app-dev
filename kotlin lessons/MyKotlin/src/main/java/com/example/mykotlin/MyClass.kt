package com.example.mykotlin

import java.awt.Color

fun main() {

//    var first=10
//    val second = 100
//
//    var x=calculate(first,second)
//    x=40
//    println(x)

    val list = mutableListOf("Mayank","Garg")



    for( i in list){
        println(i)
    }
    list.remove("Mayank")

    var car = Car(6,"vvvv")
    println(car.color)
    car.drive()


}

class Truck(id: Int, color: String) : Car(id, color){
    override fun drive() {
        super.drive()
    }



}
// writen open before class to make it open for inheritance
open class Car(var id:Int,var color: String){
//    val color:String="red"
    init {
        id = 5
        color = "hjjhjhj"
    }



    open fun drive() {
        println(id)
    }
}




/**
 * var - mutable
 * val - immutable means const, can not be changed

 *
 */

/**
 *
 * val name : String = "Garg"
 *  name = "mayank" -> wrong as val can not be reassigned
 *
 *  val name : String
 *  name = "Mayank" -> correct
 *
 */