package com.example.mykotlin

fun main() {
    var person = Person(
        "Mayank",
        "Garg",
        21
    )

    println(person)


}

data class Person(var name:String,var lastName:String,var age:Int)