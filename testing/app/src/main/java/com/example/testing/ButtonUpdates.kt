package com.example.testing

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonUpdates() {

    val counter : MutableState<Int> = remember{
        mutableStateOf(0)
    }

//    val x = counter.value
    val x = produceState(initialValue = -10) {
        // Include counter as a dependency
        value = counter.value
    }.value
    
    val y = counter.value
    Log.d("Pop", "ButtonUpdates: console recomposed!")
    
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(text = "Hello")
            CreateButton(counter)
            Text(text = "${counter.value}")
            Text(text = "${x}")
            Text(text = "$y")

        }

    }

    Log.d("Pop", "ButtonUpdates: console recomposed!?")
    
    
    
}


@Composable
fun CreateButton(counter: MutableState<Int>) {

    Button(onClick = {
        counter.value+=1
    },
        modifier = Modifier
            .height(105.dp)
            .width(105.dp)
    ) {
        Text(text = "Update",
            color = Color.White)

    }
    Log.d("Pop", "ButtonUpdates: Button recomposed")

}