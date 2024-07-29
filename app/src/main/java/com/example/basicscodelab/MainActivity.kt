package com.example.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface {
        if (shouldShowOnboarding) {
            Onboarding(continueClicked = { shouldShowOnboarding = false} )
    }
        else {
            OsList()
        }

    }
}


@Composable
fun Onboarding(continueClicked: () -> Unit, modifier: Modifier = Modifier) {


    Column (modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Welcome to number showcase")
        Button(onClick = continueClicked) {
            Text("Get Started")
        }
    }
}


@Composable
fun OsList(modifier: Modifier = Modifier) {
    val names: MutableMap<String, String> = mutableMapOf()
    for (num in 1..2000) {
        names["Number: $num"] = "The number is $num"

    }

    LazyColumn(modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        items(names.keys.toList()) { name ->
                EachShowcase(item = name, desc = names[name].toString())
        }
    }
}

@Composable
fun EachShowcase(item: String, desc: String, modifier: Modifier = Modifier) {

    val expanded = remember {
        mutableStateOf(false)
    }

    val extraPadding by animateDpAsState(
        if (expanded.value) 40.dp else 4.dp
    )

    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (!expanded.value) {
            Column(
                modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 4.dp, vertical = extraPadding)
            ) {
                Text("$item ...")
            }
            ElevatedButton(
                onClick = { expanded.value = true },
                modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "View More")

            }
        }
        else {
            Column(
                modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 4.dp, vertical = extraPadding)
            ) {
                Text(desc)
            }
            ElevatedButton(
                onClick = { expanded.value = false },
                modifier
                    .align(Alignment.CenterVertically)
//                    .padding(vertical = extraPadding)
            ) {
                Text(text = "View Less")
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun OsListPreview() {
    BasicsCodelabTheme {
        OsList()
    }
}

//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun GreetingPreview() {
//    BasicsCodelabTheme {
//        MyApp()
//    }
//}
