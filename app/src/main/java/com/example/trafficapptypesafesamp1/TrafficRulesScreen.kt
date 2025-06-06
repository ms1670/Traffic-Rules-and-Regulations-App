package com.example.trafficapptypesafesamp1

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrafficRulesScreen(navController: NavController){
    val scrollState = rememberLazyListState()

    Column {
        Text(
            text = "Traffic Rules",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        //Spacer(modifier = Modifier.height(24.dp))


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // .verticalScroll(rememberScrollState()) // Enables vertical scrolling
                .padding(16.dp),
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            GeneralRulesInfo.forEach { ruleCategory ->
                stickyHeader {
                    TrafficRulesCategoryHeader(ruleCategory.title, scrollState)
                }
                item{
                    TrafficRulesScreenCard(
                        navController, ruleCategory.title
                    )
                }
            }

        }
    }
}

//    TrafficRulesScreenCard(navController, ruleCategory)


//        LazyColumn {
//            items(ruleCategory) {ruleCategory->
//                GeneralRulesInfo.forEach { ruleCategory ->
//                    TrafficRulesScreenCard(
//                        navController = navController,
//                        ruleCategory = ruleCategory.title
//                    )
//                }
//                Spacer(modifier = Modifier.height(24.dp))
//            }
//        }


        //AnimatedVisibility by displat in by one using next button
//        AnimatedVisibility(
//            visible = true,
//            enter = fadeIn(animationSpec = tween(500))
//        ) {
//            TrafficRulesScreenCard(
//                navController = navController,
//                ruleCategory = GeneralRulesInfo[selectedCategoryIndex].title
//            )
//        }
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(
//                onClick = {
//                    selectedCategoryIndex =
//                        (selectedCategoryIndex - 1 + categoryCount) % categoryCount
//                },
//                enabled = selectedCategoryIndex > 0
//            ) {
//                Text("Previous")
//            }
//
//            Button(
//                onClick = {
//                    selectedCategoryIndex = (selectedCategoryIndex + 1) % categoryCount
//                },
//                enabled = selectedCategoryIndex < categoryCount - 1
//            ) {
//                Text("Next")
//            }
//        }


@Composable
fun TrafficRulesCategoryHeader(title: String, scrollState: LazyListState){
    val isScrolled = remember { derivedStateOf { scrollState.firstVisibleItemIndex > 0 } }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
              //  Color(0xFF2196F3), shape = RoundedCornerShape(8.dp)
                color = if (isScrolled.value) Color(0xFF1976D2).copy(alpha = 0.9f) else Color(0xFF2196F3), // Transparency effect
                shape = RoundedCornerShape(8.dp)
            ) // Blue background
           // .shadow(if (isScrolled.value) 6.dp else 0.dp, RoundedCornerShape(8.dp)) // Adds shadow when scrolling
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title.uppercase(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White // White text for contrast
        )
    }
}

@Composable
fun TrafficRulesScreenCard(navController: NavController, ruleCategory: String){

    Log.d("TrafficRulesScreen", "Category: $ruleCategory")

    val trafficBasicRules = TrafficRulesScreenDetails(ruleCategory)

    val pagerState = rememberPagerState(initialPage = 0) {
        trafficBasicRules.size
    }

    val currentPage by remember { derivedStateOf { pagerState.currentPage } }


    // Stacked Card background
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        )
//        {
//            trafficBasicRules.forEachIndexed { index, rule ->
//                Card (
//                    modifier = Modifier
//                        .fillMaxWidth(0.85f)
//                        .height(300.dp)
//                        .background(Color(0xFFFF5F5F))
//                        .offset(y = (index * 20.dp))
//                        .graphicsLayer {
//                            alpha = 1f - (index * 0.1f)
//                            scaleX = 1f - (index * 0.5f)
//                            scaleY = 1f - (index * 0.5f)
//                        },
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
//                ){
//
//                }
//
//            }
//        }

    Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                 //   .size(500.dp)
                    .fillMaxSize()
                  //  .height(320.dp)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ){
                HorizontalPager(
                    state = pagerState,
                    // pageContent = trafficBasicRules.size,
                    modifier = Modifier.fillMaxSize(),
                    pageSpacing = (-30).dp,
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    page ->
//                    val scale = 1f - (pagerState.currentPageOffsetFraction * 0.1f)
//                    val alpha = 1f - (pagerState.currentPageOffsetFraction * 0.3f)
//                    val scale by animateFloatAsState(
//                        targetValue = if (pagerState.currentPage == page) 1f else 0.9f,
//                        animationSpec = tween(durationMillis = 300)
//                    )
//                    val alpha by animateFloatAsState(
//                        targetValue = if (pagerState.currentPage == page) 1f else 0.5f,
//                        animationSpec = tween(durationMillis = 300)
//                    )

                    val scale by animateFloatAsState(
                        targetValue = if (pagerState.currentPage == page) 1f else 0.85f, // Smaller inactive cards
                        animationSpec = tween(durationMillis = 400)
                    )
                    val alpha by animateFloatAsState(
                        targetValue = if (pagerState.currentPage == page) 1f else 0.3f, // More fade effect
                        animationSpec = tween(durationMillis = 400)
                    )

                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(450.dp)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                this.alpha = alpha
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                          //  containerColor = Color(0xFFF8F8F8)
                            AppColors.SoftWhite
                            //Color(0xFFE7FF99)
                        ),

                        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color(0xFFF8F8F8)
                                )
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = trafficBasicRules[page].imageResId),
                                contentDescription = "",
                                modifier = Modifier

                                    .border(
                                        BorderStroke(1.dp,
                                            Color(
                                                //0xFFF3F3F3
                                                0xFFA9F9A9
                                            )
                                        ),
                                        RoundedCornerShape(
                                            topStart = 8.dp,
                                            topEnd = 8.dp,
                                            bottomStart = 0.dp,
                                            bottomEnd = 0.dp,
                                        ),
                                    )
                                    .size(180.dp)
                                    .background(
                                        Color(0xFFF8F8F8)
                                    )
                                    .padding(16.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = trafficBasicRules[page].title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = trafficBasicRules[page].description,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))

//                            Button(onClick = {/* Navigate to detailed rule page */}) {
//                                Text("Learn More")
//                            }
                        }
                    }

                }

            }
            // Fading Edge Effect
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(
//                            Brush.horizontalGradient(
//                                colors = listOf(Color.Transparent, Color(0xFF2195F1).copy(alpha = 0.5f))
//                            )
//                        )
//                )

            }

            // Page Indicator (Dots)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(trafficBasicRules.size) { index ->
                    val color = if (index == currentPage) Color.Blue else Color.Gray
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .padding(4.dp)
                            .background(color, shape = CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


        }



@Preview(showBackground = true)
@Composable
fun PreviewTrafficRulesScreenCard() {
    // Creating a fake NavController for preview (not needed in a real app)
    val fakeNavController = rememberNavController()

    TrafficRulesScreenCard(
        navController = fakeNavController,
        ruleCategory = "General Traffic Rules" // Sample category
    )
}



data class GeneralRules(
    var title: String,
)

var GeneralRulesInfo = listOf(
    GeneralRules("General Traffic Rules"),
    GeneralRules("Driving Rules"),
    GeneralRules("Pedestrian Safety"),
    GeneralRules("Two-Wheeler Rules"),
    GeneralRules("Heavy Vehicles & Public Transport"),
    GeneralRules("Penalty & Fines"),
)

data class GeneralRulesDes(
    var title: String,
    var imageResId: Int,
    var description: String

)

fun TrafficRulesScreenDetails(ruleCategory: String): List<GeneralRulesDes> {
    return when (ruleCategory) {
        "General Traffic Rules" -> listOf(
            GeneralRulesDes("Obey Traffic Signals", R.drawable.mandatory_sign_img, "Red means stop, green means go."),
            GeneralRulesDes("Lane Discipline", R.drawable.compulsory_signs_img, "Stay in your lane; avoid sudden changes."),
            GeneralRulesDes("Use Indicators", R.drawable.stop_sign_img, "Always signal before turning."),
            GeneralRulesDes("Overtake from Right", R.drawable.mandatory_sign_img, "Always overtake from the right side."),
            GeneralRulesDes("Maintain Safe Distance", R.drawable.compulsory_signs_img, "Keep enough space between vehicles.")
        )

        "Driving Rules" -> listOf(
            GeneralRulesDes("Seatbelts are Mandatory", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Don't use mobile phones while driving", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Drunk driving is a serious offense", R.drawable.mandatory_sign_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Speed limits vary by area ", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("No honking in silent zones", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
        )

        "Pedestrian Safety" -> listOf(
            GeneralRulesDes("Seatbelts are Mandatory", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Don't use mobile phones while driving", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Drunk driving is a serious offense", R.drawable.mandatory_sign_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Speed limits vary by area ", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("No honking in silent zones", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
        )

        "Two-Wheeler Rules" -> listOf(
            GeneralRulesDes("Seatbelts are Mandatory", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Don't use mobile phones while driving", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Drunk driving is a serious offense", R.drawable.mandatory_sign_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Speed limits vary by area ", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("No honking in silent zones", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
        )

        "Heavy Vehicles & Public Transport" -> listOf(
            GeneralRulesDes("Seatbelts are Mandatory", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Don't use mobile phones while driving", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Drunk driving is a serious offense", R.drawable.mandatory_sign_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Speed limits vary by area ", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("No honking in silent zones", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
        )

        "Penalty & Fines" -> listOf(
            GeneralRulesDes("Seatbelts are Mandatory", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Don't use mobile phones while driving", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Drunk driving is a serious offense", R.drawable.mandatory_sign_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("Speed limits vary by area ", R.drawable.prohibition_signs_img, "All passengers must wear seatbelts."),
            GeneralRulesDes("No honking in silent zones", R.drawable.compulsory_signs_img, "All passengers must wear seatbelts."),
        )

        else -> emptyList()  // Return an empty list if the category doesn't match
    }
}

@Preview
@Composable
fun TrafficRulesScreenNavigation() {
    val navController = rememberNavController()

    TrafficRulesScreen(
        navController, // Pass a sample category
    )
}