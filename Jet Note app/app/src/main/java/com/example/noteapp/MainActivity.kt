 package com.example.noteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.screen.NoteScreen
import com.example.noteapp.screen.NoteViewModel
import com.example.noteapp.ui.theme.NoteappTheme
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
 class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                val noteViewModel : NoteViewModel by viewModels()
                    NotesApp(noteViewModel = noteViewModel)


                }
            }
        }
    }
}

 @Composable
fun NotesApp(noteViewModel: NoteViewModel) {

     val noteList = noteViewModel.noteList.collectAsState().value

     NoteScreen(notes = noteList,
         onAddNote = {
                     noteViewModel.addNote( it)
         },
         onRemove = {
             noteViewModel.removeNote(it)
         })


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteappTheme {

    }
}