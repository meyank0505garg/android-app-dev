package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@Composable
//this function will add beautiful ui to each of items of list <T> given
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
//        .height(150.dp)
        .clickable {
            onItemClick(movie.id)

        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

//        ********************************************
        Column(modifier = Modifier.padding(4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp),
                    color = Color.Black,
                    shape = CircleShape,
                    shadowElevation = 4.dp
                ) {
//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
                    AsyncImage(
//                    model = movie.images[0],
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.images[0])
                            .crossfade(true).build(),
                        modifier = Modifier.clip(CircleShape),
                        contentDescription = "Movie Poster",
                    )


                }

                Column(modifier = Modifier.padding(4.dp)) {

                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Director : ${movie.director}",
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.titleMedium

                    )
                    Text(
                        text = "Released : ${movie.year}",
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.titleMedium

                    )




                    Icon(imageVector = if (!expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Down arrow",
                        modifier = Modifier
                            .size(25.dp)
                            .align(alignment = CenterHorizontally)
                            .clickable {
                                expanded = !expanded


                            },
                        tint = Color.Black)


                }


            }

            AnimatedVisibility(visible = expanded) {

                Column(modifier = Modifier.padding(7.dp)) {

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 17.sp

                                )
                            ) {
                                append("Plot : ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light

                                )
                            ) {
                                append(movie.plot)
                            }
                        },
                        modifier = Modifier.padding(6.dp)
                    )
                    Divider(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Director : ${movie.director}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Actors : ${movie.actors}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Rating : ${movie.rating}",
                        style = MaterialTheme.typography.bodyMedium
                    )


                }


            }


        }


    }


}