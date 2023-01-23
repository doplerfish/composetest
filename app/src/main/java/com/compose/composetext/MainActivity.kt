package com.compose.composetext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            DefaultPreview()
//            Greeting(name = "Amir!")
//            DummyList()
//            RecyclerViewList()
//            Snackbar { Text(text = "Hello Amir") }
//            TextFiledWithSnackbar()
//            ImageCard(painter = painterResource(id = R.drawable.kermit), contentDescription = "Kermit in the snow", title = "Kermit is playing in the snow")
//            ColorboxPreview()
//            ComposeLayout()


        }
    }

    @Composable
    fun ComposeLayout() {
        // Note: add dependency for constraintlayout-compose
        val constraints = ConstraintSet {
            val greenBox = createRefFor("greenbox")
            val redBox = createRefFor("redbox")

            constrain(greenBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            constrain(redBox) {
                top.linkTo(parent.top)
                start.linkTo(greenBox.end)
                end.linkTo(parent.end)
//                    width = Dimension.fillToConstraints
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            // Put them one after the other
            //createHorizontalChain(greenBox, redBox)

            // Put them one after the other - packed together
            createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
        }
        ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox")
            )
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redbox")
            )
        }
    }

    @Composable
    fun ColorboxPreview() {
        Column(Modifier.fillMaxSize()) {
            val color = remember {
                mutableStateOf(Color.Yellow)
            }
            ColorBox(
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
                color.value,
            ) {
                color.value = it
            }

            Box(
                modifier = Modifier
                    .background(color.value)
                    .weight(1f)
                    .fillMaxSize()
            )
            {

            }
        }
    }

    @Composable
    fun RecyclerViewList() {
        LazyColumn {
            itemsIndexed(
                listOf("This", "is", "Jetpack", "Compose")
            ) { index, string ->
                Text(
                    text = string,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        }
    }

    @Composable
    fun DummyList() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            for (i in 1..50) {
                Text(
                    text = "Item $i",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        }
    }

    @Composable
    fun TextFiledWithSnackbar() {
        val scaffoldState = rememberScaffoldState()
        var textFieldState by remember {
            mutableStateOf("")
        }

        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                TextField(
                    value = textFieldState,
                    label = { Text("Enter your name") },
                    onValueChange = {
                        textFieldState = it
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                    }
                }) {
                    Text(text = "Pls greet me")
                }
            }
        }
    }

    @Composable
    fun ColorBox(
        modifier: Modifier = Modifier,
        color: Color,
        updateColor: (Color) -> Unit
    ) {
        Box(modifier = modifier
            .background(color)
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            })
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
        modifier: Modifier = Modifier
    ) {
        Box(
            Modifier
                .fillMaxSize(0.5f)
                .padding(16.dp)
        ) {
            Card(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp
            ) {
                Box(modifier = Modifier.height(200.dp)) {
                    Image(
                        painter = painter,
                        contentDescription = contentDescription,
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ), startY = 300f
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Greeting("Amir")
    }
}