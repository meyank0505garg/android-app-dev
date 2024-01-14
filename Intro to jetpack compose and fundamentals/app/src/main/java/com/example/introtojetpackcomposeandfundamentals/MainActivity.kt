



package com.example.introtojetpackcomposeandfundamentals

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtojetpackcomposeandfundamentals.ui.theme.IntroToJetpackComposeAndFundamentalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroToJetpackComposeAndFundamentalsTheme {
//                 A surface container using the 'background' color from the theme
                Myapp()
            }
        }
    }
}

@Composable

fun Myapp(){
//    var money_counter by remember{
//        mutableStateOf(0)
//    }
    var money_counter by remember{
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
        ,
        color = Color(0xFF546E7A)

    ) {
         // Text(text = "Hello My name is Mayank Garg")
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment= Alignment.CenterHorizontally


        ) {

            Text(text = "$${money_counter}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 29.sp

                )

            )

            Spacer(modifier = Modifier.height(10.dp))

            val update_money_counter:(Int) -> Unit={
            money_counter=it+1




        }

            CreateCircle(money_counter,update_money_counter)
        }


    }

}

@SuppressLint("UnrememberedMutableState")
//@Preview

@Composable
fun CreateCircle(money_counter:Int=0,update_money_counter:(Int)->Unit) {

    Card(modifier = Modifier
        .padding(3.dp)
        .size(105.dp)
        .clickable {
//            //money_counter+=1
            update_money_counter(money_counter)
//            Log.d("tap", "CreateCircle: tapped!! and value is ${money_counter}")
        }
        ,
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()

        ) {
            Text(text = "Tap",modifier = Modifier)

        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToJetpackComposeAndFundamentalsTheme {
        Myapp()
    }
}
//
//
//