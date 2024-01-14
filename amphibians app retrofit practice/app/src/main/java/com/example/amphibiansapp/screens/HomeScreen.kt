package com.example.amphibiansapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.ViewModel.AppState
import com.example.amphibiansapp.data.DataStructure

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    appState : AppState
) {
    

    Scaffold(
        topBar = { HomeScreenAppBar() },

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {


//            when (appState){
//                is AppState.Success -> {
//                    LazyColumn( ) {
//                        items(appState.data){item ->
//
//                            HomeScreenLazyColumnItem(data = item,
//                                modifier = Modifier.padding(4.dp))
//
//
//                        }
//
//
//                    }
//                }
//                is AppState.Error -> {
//                    ErrorScreen()
//                }
//                is AppState.Loading -> {
//                    LoadingScreen()
//                }
//            }

            when(appState){
                is AppState.Success -> {
                    Log.d("final_data", "HomeScreen: Success ${appState.data.toString()} ")

                }
                is AppState.Error -> {
                    Log.d("Api", "HomeScreen: Error ")
                }
                is AppState.Loading -> {
                    Log.d("Api", "HomeScreen: Loading ")
                }
            }
            
            


        }




    }

}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//
//    HomeScreen(appState = )
//
//}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppBar() {



    CenterAlignedTopAppBar(title = {
        Text(stringResource(R.string.app_name))
    }
        ,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color(238,242,247)




        )

    )

}

@Composable
fun HomeScreenLazyColumnItem(
     data : DataStructure,
    modifier: Modifier = Modifier
) {
    
    Card(
        modifier = modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(4.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(238,242,247)
        ),

    ) {
       Column(
           modifier = modifier
       ) {
           Text(text = "${data.name} (${data.type})",
               modifier = modifier
                   .align(CenterHorizontally),
               style = MaterialTheme.typography.bodyLarge,
               fontWeight = FontWeight.Bold


           )

           ImageContainer(data.imgSrc)

           Text(text = data.description,
               modifier = modifier,
               style = MaterialTheme.typography.bodyLarge,
               )
           
       }
        
    }
    
    
}

@Composable
fun ImageContainer(
    photo:String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)

        ,
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.cardElevation(8.dp)


    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(),
            error = painterResource(R.drawable.ic_broken_image),
        )

    }

}

//@Preview
//@Composable
//fun ImageContainerPreview(){
//    ImageContainer()
//}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Loading failed", modifier = Modifier.padding(16.dp))
//        Button(onClick = retryAction) {
//            Text(stringResource(R.string.retry))
//        }
    }
}
