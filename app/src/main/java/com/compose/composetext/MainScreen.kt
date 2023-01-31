package com.compose.composetext

import android.view.MotionEvent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.*
import kotlin.random.Random

private const val TAG = "MainScreen"


@Composable
fun MainScreen() {
//    DefaultPreview()
//    Greeting(name = "Amir!")
//    ArrangementAndAlignment()
//    OffsetMovesItemInsideItsParent()
//    ClickListener()
//    DraggableHorizontally()
//    DraggableBox()
//    DummyList()
//    RecyclerViewList()
//    Snackbar { Text(text = "Hello Amir") }
//    TextFiledWithSnackbar()
//    ImageCard()
//    ColorboxPreview()
//    ConstraintLayout()
//    LaunchedEffectDemo()
//    MyButton()
//    LaunchedEffectWhenValueChanges()
//    NetworkCallWithProduceState()
//    SizeAndColorAnimations()
//    ShowCircularProgressBar()
//    ShowDraggableMusicKnob()
//    ShowTimer()
//    TouchableArch()
//    ShowBottomTabsView()
}

@Composable
fun ShowBottomTabsView() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    data class Screen(val title: String, val background: Color)

    var screen by remember {
        mutableStateOf(Screen("Home", Color.Green))
    }

    LaunchedEffect(key1 = selectedTabIndex){
        when (selectedTabIndex){
            0 -> screen = Screen("Home", Color.Green)
            1 -> screen = Screen("Bookings", Color.Red)
            2 -> screen = Screen("Search", Color.Yellow)
            3 -> screen = Screen("Profile", Color.Blue)
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(screen.background)
        .padding(bottom = 20.dp)

    ) {
        Text(modifier = Modifier
            .align(Alignment.Center
            ),
            text = screen.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,)
    }
    Box(modifier = Modifier.fillMaxSize()) {

        TabsView(
            imageWithText = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_home),
                    text = "Home"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_bookings),
                    text = "Bookings"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_search),
                    text = "Search"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_person),
                    text = "Profile"
                )
            ),
            modifier = Modifier.align(Alignment.BottomCenter).background(Color.White)
        ) { selectedIndex ->
            selectedTabIndex = selectedIndex
        }
    }

}

data class ImageWithText(val image: Painter, val text: String)

@Composable
fun TabsView(
    modifier: Modifier = Modifier,
    imageWithText: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {

    var selectedTopIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(
        selectedTabIndex = selectedTopIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        imageWithText.forEachIndexed { index, item ->
            Tab(
                selected = selectedTopIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTopIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    tint = if (selectedTopIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }

    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TouchableArch() {

    val limitingAngle: Float = 25f

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var value by remember {
        mutableStateOf(0.6f)
    }

    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }

    var color by remember {
        mutableStateOf(Color.Green)
    }

    LaunchedEffect(key1 = value) {
        val r = if (value > 0.66) (255 * value * value).toInt() else 0
        val g = if (value > 0.33 && value < 0.66) (255 * (value * value + 0.33)).toInt() else 0
        val b = if (value < 0.33) (255 * (value * value + 0.66)).toInt() else 0
        color = Color(r, g, b, 255)
    }

    Surface(color = Color(0xFF101010), modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .onSizeChanged {
                    size = it
                }
                .onGloballyPositioned {
                    val windowBounds = it.boundsInWindow()
                    centerX = windowBounds.size.width / 2f
                    centerY = windowBounds.size.height / 2f
                }
                .pointerInteropFilter { event ->
                    touchX = event.x
                    touchY = event.y
                    val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
                    when (event.action) {
                        MotionEvent.ACTION_DOWN,
                        MotionEvent.ACTION_MOVE -> {
                            if (angle !in -limitingAngle..limitingAngle) {
                                val fixedAngle = if (angle in -180f..-limitingAngle) {
                                    360f + angle
                                } else {
                                    angle
                                }

                                val percent =
                                    (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                                value = percent
                                true
                            } else false
                        }
                        else -> false
                    }
                }) {
                Canvas(
                    modifier = Modifier
                        .size(200.dp)
                ) {
                    drawArc(
                        color = Color.DarkGray,
                        size = Size(size.width.toFloat(), size.height.toFloat()),
                        startAngle = -215f,
                        sweepAngle = 250f,
                        useCenter = false,
                        style = Stroke(20f, cap = StrokeCap.Round),
                    )

                    drawArc(
                        color = color,
                        size = Size(size.width.toFloat(), size.height.toFloat()),
                        startAngle = -215f,
                        sweepAngle = 250f * value,
                        useCenter = false,
                        style = Stroke(20f, cap = StrokeCap.Round),
                    )

                    val center = Offset(size.width / 2f, size.height / 2f)
                    val beta = (250f * value + 145f) * (PI / 180f).toFloat()
                    val r = size.width / 2f
                    val a = cos(beta) * r
                    val b = sin(beta) * r
                    drawPoints(
                        listOf(Offset(center.x + a, center.y + b)),
                        pointMode = PointMode.Points,
                        color = color,
                        strokeWidth = 80f,
                        cap = StrokeCap.Round,
                    )
                }


            }
        }

    }
}

@Composable
fun ShowTimer() {
    Surface(color = Color(0xFF101010), modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Timer(
                totalTime = 100L * 1000L,
                handleColor = Color.Green,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color(0xFF37B900),
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0L && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round,
            )
        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isTimerRunning || currentTime <= 0L) {
                    Color.Red
                } else {
                    Color.Green
                }
            )
        ) {
            Text(
                text = if (isTimerRunning && currentTime >= 0L) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Restart"
            )
        }
    }
}

@Composable
fun ShowDraggableMusicKnob() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }
            val barCount = 20

            DraggableMusicKnob(
                modifier = Modifier.size(100.dp),
            ) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DraggableMusicKnob(
    modifier: Modifier,
    limitingAngle: Float = 25f,
    onValueChange: (Float) -> Unit,
) {

    var rotation by remember {
        mutableStateOf(limitingAngle)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }

                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}


@Composable
fun ShowCircularProgressBar() {

    val currentPercentage = remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(key1 = true) {
        for (i in 1..100) {
            delay(10)
            currentPercentage.value = (i / 100).toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressBar(percentage = currentPercentage.value, number = 100)

    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 100.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius)) {
            drawArc(
                color = color,
                -90f, // the angle where the arc starts
                360 * currentPercentage.value, // the angle until which we want to draw the arc
                useCenter = false, // otherwise the arc will be connected with the center
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (currentPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SizeAndColorAnimations() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState, // takes this size state and translate it to smooth animate
        // More parameters for the animation
        tween(
            durationMillis = 300, // animation duration
            delayMillis = 100, // animation delay
            easing = LinearOutSlowInEasing // Easing allows transitioning elements to speed up and slow down, rather than moving at a constant rate.
        ),
//        spring(Spring.DampingRatioHighBouncy) // Bouncing
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(color), contentAlignment = Alignment.Center
    ) {
        Button(onClick = { sizeState += 50.dp }) {
            Text(text = "Increase size")
        }

    }
}

@Composable
fun NetworkCallWithProduceState() {
    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        val networkCallResult = produceState(initialValue = "fetching daily article...") {
            value = getArticleFromServer()
        }
        Text(text = "${networkCallResult.value}")
    }
}

suspend fun getArticleFromServer(): String {
    delay(3000L)
    return "Eadwig ou Edwy, né vers 940 ou 941 et mort le 1er octobre 959, est roi des Anglais de 955 à sa mort. Il est le fils aîné d'Edmond Ier. Trop jeune pour succéder à son père lorsque celui-ci est tué, en 946, il ne devient roi qu'à la mort de son oncle Eadred. Dès son avènement, il entre en conflit avec l'abbé Dunstan de Glastonbury, qui est contraint de s'exiler en Flandre. Il effectue de très nombreuses donations au cours de sa première année de règne, ce qui pourrait refléter une tentative de s'attacher des fidèles ou de récompenser ses partisans au détriment de l'entourage de ses prédécesseurs. En 957, Eadwig partage l'Angleterre avec son frère cadet Edgar, qui en reçoit la moitié septentrionale. Les sources présentent cette division comme la conséquence d'une révolte des ennemis d'Eadwig, mais il est également possible qu'elle ait été consentie et peut-être même prévue dès son arrivée au pouvoir. L'année suivante, l'archevêque Oda de Cantorbéry procède à l'annulation du mariage d'Eadwig au prétexte qu'il est trop proche parent de sa femme Ælfgifu. Eadwig meurt en 959 sans laisser d'enfants et Edgar devient seul roi de toute l'Angleterre. Il souffre d'une mauvaise réputation posthume alimentée par les hagiographes et chroniqueurs ultérieurs, qui le décrivent comme irresponsable et incompétent pour mieux encenser Dunstan et les autres tenants de la réforme bénédictine anglaise, un courant religieux qui atteint son apogée à l'époque d'Edgar. Les historiens modernes commencent à remettre en question le portrait négatif d'Eadwig à la fin du xxe siècle au profit d'une approche plus nuancée de son règne, qui reste difficile à cerner en raison de sources peu nombreuses et parfois contradictoires."
}

@Composable
fun LaunchedEffectWhenValueChanges() {

    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        var counter by remember {
            mutableStateOf(0)
        }

        // Every 5th click launch effect and show snackbar
        if (counter % 5 == 0 && counter > 0) {
            LaunchedEffect(key1 = scaffoldState) {
                scaffoldState.snackbarHostState.showSnackbar("Hello")
            }
        }
        Button(onClick = { counter++ }) {
            Text("Click me: $counter")
        }
    }
}

@Composable
fun DraggableBox() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(Color.Green)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }

        )
    }
}

@Composable
fun DraggableHorizontally() {
    var offsetX by remember { mutableStateOf(0f) }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Drag me",
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    },
                )
        )
    }
}


@Composable
fun ClickListener() {
    var clicks by remember {
        mutableStateOf(0)
    }
    Text(text = "$clicks clicks", modifier = Modifier.clickable {
        clicks += 1
    })
}

@Composable
// For margin result, use Spacer between the items (see below)
fun OffsetMovesItemInsideItsParent() {
    Column {
        Text(
            modifier = Modifier
                .offset(0.dp, 20.dp)
                .background(Color.Green), text = "First Text"
        )
//        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Second Text")

    }

}

// The difference between Arrangement and Alignment:
// Arrangement is on the main axis (like driving direction in a street)
// Alignment is on the cross axis (like crossing the street)
@Composable
fun ArrangementAndAlignment() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "text 1")
        Text(text = "text 2")
    }
}


@Composable
fun MyButton() {

    Column() {
        var clicks by remember {
            mutableStateOf(0)
        }

        Button(onClick = { clicks += 1 }) {
            Text(text = "$clicks clicks")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Another button")
        }
    }


}

/*@Composable
fun LaunchedEffectFlowDemo(viewModel: LaunchedEffectViewModel) {

    val scaffoldState = rememberScaffoldState()

    // Will not be executed when some value changes, because the key is true and not a value that changes.
    // This allows us to launch this composable only once, even though it uses values from outside
    LaunchedEffect(key1 = true){
        viewModel.sharedFlow.collect { event ->
            Log.d(TAG, "LaunchedEffectFlowDemo: event = $event")
            when(event){
                is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar -> {
                    Log.d(TAG, "LaunchedEffectFlowDemo: message")
                    scaffoldState.snackbarHostState.showSnackbar("event.message")
                }
                is LaunchedEffectViewModel.ScreenEvents.Navigate -> {

                }
            }

        }
    }
}*/

@Composable
// Launched Effect is similar to useEffect hook in react
fun LaunchedEffectDemo() {
    var changeableState by remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberScaffoldState()

    if (changeableState) {
        LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar("Test")
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Button(onClick = { changeableState = !changeableState }) {
            Text(text = "Toggle")
        }
    }
}

@Composable
fun ConstraintLayout() {
    // Note: add dependency for constraintlayout-compose in app gradle
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
        ) {

        }
    }
}

@Composable
fun RecyclerViewList() {
    LazyColumn {
        itemsIndexed(
            listOf(
                "This",
                "is",
                "Jetpack",
                "Compose",
                "Recycler",
                "View",
                "List",
                "and",
                "it",
                "is",
                "so",
                "much",
                "easier",
                "!!!"
            )
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

    // Scaffold is an implementation of Material Design, a comprehensive design system for creating digital interfaces
    Scaffold(
        modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(value = textFieldState, label = { Text("Enter your name") }, onValueChange = {
                textFieldState = it
            }, singleLine = true, modifier = Modifier.fillMaxWidth()
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
    modifier: Modifier = Modifier, color: Color, updateColor: (Color) -> Unit
) {
    Box(modifier = modifier
        .background(color)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f
                )
            )
        })
}

@Composable
fun ImageCard() {
    val painter = painterResource(id = R.drawable.kermit)
    val contentDescription = "Kermit in the snow"
    val title = "Kermit is playing in the snow"

    Box(
        Modifier
            .fillMaxSize(0.5f)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp), elevation = 5.dp
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
                                    Color.Transparent, Color.Black
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
