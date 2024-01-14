package com.example.noteapp.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.util.formatDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes : List<Note>,
    onAddNote: (Note) -> Unit,
    onRemove: (Note) -> Unit,

) {

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val context= LocalContext.current


    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
//                          Text(text = "jetapp") or their is another way. to access the app name in res
                          Text(text = stringResource(id = R.string.app_name))

                          }, actions = {
                              Icon(imageVector = Icons.Rounded.Notifications , contentDescription ="Top App Bar Icon" )
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFDADFE3))


        )
        //contents


        Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
            NoteInputText(text = title, label = "Title",
                onTextChange = {
                               if(it.all {char->
                                       char.isLetter() || char.isWhitespace()

                                   }) {title = it}


                },
                modifier = Modifier.padding(
                    top=9.dp,
                    bottom = 8.dp
                )
            )

            NoteInputText(text = description,
                label = "Add a note",
                onTextChange = {
                    if(it.all {char->
                            char.isLetter() || char.isWhitespace()

                        }) {description = it}


                },
                modifier = Modifier.padding(
                    top=9.dp,
                    bottom = 8.dp
                )
            )

            NoteButton(text = "Save", onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()){
                    onAddNote(Note(title=title , description = description))
                    title=""
                    description=""
                    Toast.makeText(context,
                        "Note Added",
                        Toast.LENGTH_SHORT).show()
                }
            })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn( ) {
            items(notes) {note->
//                Text(text = note.title)

                    NoteRow(note = note, onNoteClicked = {
                        onRemove(note)
                    })
            }
        }


    }

}

@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note:Note,
            onNoteClicked : (Note) -> Unit) {

    Surface(modifier = modifier
        .padding(3.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 0.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp ) {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)

                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start ) {
            Text(text = note.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )

            Text(text = note.description,
                style = MaterialTheme.typography.bodyMedium)

            Text(text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.bodySmall)


        }


    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(),
        onAddNote = {},
        onRemove = {})
}