package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.InutField
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Myapp {
//                Text(text = "Hello Again")
//                TopHeader()
                MainContent()
            }

        }
    }
}


@Composable

fun Myapp(content : @Composable ()-> Unit) {

    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
//            Greeting("Android")
        }

    }


}


@Preview

@Composable


fun TopHeader(totalPerPerson:Double = 0.0) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
//        .clip(shape = RoundedCornerShape(corner = CornerSize(40.dp)))
        .padding(20.dp)

        ,
        color = Color(0xFFE9D7F7),
        shape = RoundedCornerShape(corner = CornerSize(7.dp))


    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = "Total Per Person",
            fontSize = 20.sp
            )
            val total = "%.2f".format(totalPerPerson)
            Text(text = "$${total}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium

            )

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview

@Composable
fun MainContent() {

    Column {
//        TopHeader()

        BillForm() {bilAmt ->
            Log.d("AMT", "MainContent: ${bilAmt}")

        }

    }


    



}

//@Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun BillForm(modifier: Modifier = Modifier,
             onValChange:(String) -> Unit = {}

             ){

    var totalBillState = remember {
        mutableStateOf("")
    }

    var validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()

    }

    var keyboardController = LocalSoftwareKeyboardController.current
    var sliderPositionState = remember {
        mutableStateOf(0f)
    }

    var splitByState = remember {
        mutableStateOf(1)
    }

    var range = IntRange(start = 1, endInclusive = 100)

    var tipPercentage = (sliderPositionState.value * 100).toInt()

    var tipAmountState = remember {
        mutableStateOf<Double>(0.0)
    }
    var totalPerPersonState = remember {
        mutableStateOf(0.0)
    }


    TopHeader(totalPerPersonState.value)



    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp,
            color = Color.LightGray

        )
    ) {

        Column (modifier=Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start

            ) {
            InutField(
                valueState = totalBillState ,
                labelId = "Enter Bill" ,
                enabled = true ,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if(!validState) return@KeyboardActions
                    // Todo onvaluechange
                    onValChange(totalBillState.value.trim())

                    keyboardController?.hide()

                }

            )

//            if(validState){
                Row(modifier=Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start

                    ) {

                    Text(text = "Split",
                        modifier=Modifier.align(Alignment.CenterVertically)

                        )

                    Spacer(modifier = Modifier.width(120.dp))

                    Row(modifier=Modifier.padding(horizontal = 3.dp)) {
                        RoundIconButton( imageVector = Icons.Default.KeyboardArrowDown , onClick = {
//                            Log.d("decrease", "BillForm: decrease icon clicked")

                            splitByState.value = if(splitByState.value > 1) splitByState.value - 1  else 1

                            totalPerPersonState.value = calculateTotalPerson(totalBill = totalBillState.value.toDouble(),
                                splitBy=splitByState.value,tipPercentage=tipPercentage)


                        })
                        
                        Text(text = "${splitByState.value}",
                            modifier= Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 9.dp)

                            )

                        RoundIconButton( imageVector = Icons.Default.KeyboardArrowUp , onClick = {
//                            Log.d("increase", "BillForm: increase icon clicked")

                            if(splitByState.value < range.last){
                                splitByState.value +=1
                            }else{

                            }
                            totalPerPersonState.value = calculateTotalPerson(totalBill = totalBillState.value.toDouble(),
                                splitBy=splitByState.value,tipPercentage=tipPercentage)


                        })

                    }



                }
                
//                Text(text = "Split")
//            }else{
//
//                Box() {
//
//                }
//
//            }

            // Tip Row
            Row(modifier = Modifier.padding(3.dp,12.dp)) {
                Text(text = "Tip",
                    modifier=Modifier.align(alignment = Alignment.CenterVertically)

                )
                
                Spacer(modifier = Modifier.width(178.dp))

                Text(text = "$${tipAmountState.value}",
                    modifier=Modifier.align(alignment = Alignment.CenterVertically)

                )


            }

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()


                )
            {
                Text(text = "${tipPercentage}%")
                Spacer(modifier = Modifier.height(20.dp))

                // slider
                Slider(value = sliderPositionState.value,
                    onValueChange = {newVal ->
                        sliderPositionState.value=newVal
                        tipAmountState.value=calculateTotalTip(totalBillState.value.toDouble(),tipPercentage)
                        totalPerPersonState.value = calculateTotalPerson(totalBill = totalBillState.value.toDouble(),
                            splitBy=splitByState.value,tipPercentage=tipPercentage)
//                        Log.d("Slider", "BillForm: $newVal")

                },
                    steps =100,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onValueChangeFinished = {

                    }

                )




            }


        }

    }



}

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {

    return  if(totalBill>1 && totalBill.toString().isNotEmpty()) (totalBill*tipPercentage)/100
        else 0.0

}

fun calculateTotalPerson(
    totalBill: Double,splitBy:Int,
    tipPercentage: Int
):Double {

    val Bill = calculateTotalTip(totalBill,tipPercentage) + totalBill
    return Bill/splitBy


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {

        Myapp {
            Text(text = "Hello Again")
        }

    }
}