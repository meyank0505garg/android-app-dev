package com.example.mykotlin

fun main() {
//    println("hrkko")

    val button= Button(label = "ok done ppp")
    button.onClick("this is message")

}

class Button(val label:String):ClickEvent{
    override fun onClick(message: String) {
        println("clicked !!${label} and $message")
    }


}

interface ClickEvent {

    fun onClick(message:String)
}

