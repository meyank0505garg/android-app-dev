package com.example.movieapp.Screen.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),

            title = {
                Text("Movies")
            }
        )
     } ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainContent(navController=navController)
        }

    }


}


@Composable
fun MainContent(
    navController: NavController,
    movieList:List<Movie> = getMovies() ) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn{
            items(items=movieList){
                MovieRow(movie = it){moviename->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$moviename")
//                    Log.d("movierow", "MainContent: ${it}")
                }


            }
        }


    }

}