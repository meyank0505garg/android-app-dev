package com.example.movieapp.Screen.Detail

import android.annotation.SuppressLint
import android.text.Layout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, movieId: String?){

    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
            title = {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(imageVector = Icons.Default.ArrowBack , contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }

                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Text("Movies")

                }

            }
        )
                 } ){


            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(it),

        ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            MovieRow(movie = newMovieList[0])
            
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images")
            LazyRow{
                items(newMovieList[0].images){image ->
                    Card(modifier = Modifier.padding(4.dp)
                        .size(140.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )

                    ) {

                        AsyncImage(
//                    model = movie.images[0],
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true).build(),
                            modifier = Modifier.fillMaxSize(),

                            contentDescription = "Movie Poster",
                        )

                    }
                }
            }

            Text(text = newMovieList[0].title,
                style = MaterialTheme.typography.bodyLarge)

        }


    }
        
    }


    
}