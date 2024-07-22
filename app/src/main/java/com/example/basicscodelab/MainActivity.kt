package com.example.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val names: Map<String, String> = mapOf(
        "Android" to "Android is a great mobile operating system",
        "iOS" to "iOS is an operating system made by Apple for the use in Apple mobile devices",
        "Lunix" to " I'd just like to interject for a moment. What you're refering to as Linux, is in fact, GNU/Linux, or as I've recently taken to calling it, GNU plus Linux. Linux is not an operating system unto itself, but rather another free component of a fully functioning GNU system made useful by the GNU corelibs, shell utilities and vital system components comprising a full OS as defined by POSIX.",
        "Bindows" to "Spyware you pay for. Optionally can be used as an operating system"
    )

    Column (modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)){
            OsList(names)
    }
}


@Composable
fun OsList(names: Map<String, String>, modifier: Modifier = Modifier) {
        Column() {
            for (oses in names.keys) {
                EachShowcase(item = oses, desc = names[oses].toString())
            }
    }
}

@Composable
fun EachShowcase(item: String, desc: String, modifier: Modifier = Modifier) {

    val expanded = remember {
        mutableStateOf(false)
    }

    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (!expanded.value) {
            Column(modifier.weight(1f).align(Alignment.CenterVertically).padding(horizontal = 4.dp)) {
                Text("$item ...")
            }
            ElevatedButton(onClick = {expanded.value = true}) {
                Text(text = "View More")
                
            }
        }
        else {
            Column(modifier.weight(1f).align(Alignment.CenterVertically).padding(horizontal = 4.dp)) {
                Text(desc)
            }
            ElevatedButton(onClick = {expanded.value = false}, modifier.align(Alignment.CenterVertically)) {
                Text(text = "View Less")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}