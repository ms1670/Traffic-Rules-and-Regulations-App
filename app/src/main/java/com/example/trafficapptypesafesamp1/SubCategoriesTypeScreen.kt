@file:OptIn(UiToolingDataApi::class)

package com.example.trafficapptypesafesamp1

import android.icu.text.CaseMap.Upper
import android.text.style.BackgroundColorSpan
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun SubCategoriesTypeScreen(navController: NavController, category: String, SubCategoryList: String, ){
    val Signs_SubType = SubCatType(category, SubCategoryList)

    Column {
        Text(
            text = SubCategoryList.uppercase(), // Displaying the category name as title
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        LazyVerticalGrid (
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ){
            items(Signs_SubType){ SignSubType ->
                SignsSupTypeCardSquare(SubCatType = SignSubType)
            }
        }
    }

}

@Composable
fun SignsSupTypeCardSquare(SubCatType: SignSubType) {
    val showDialog = remember { mutableStateOf(false) }

    var isFlipped by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f, // Flips 180 degrees
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing), // Smooth animation
        label = "FlipAnimation"
    )

    // Gradually animate flip when isFlipped changes
//    LaunchedEffect(isFlipped) {
//        if (isFlipped) {
//            rotation = 180f // Start slow, visible flip
//            delay(600) // Wait for animation to finish before showing dialog
//            showDialog.value = true
//        }
//    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .clickable {
                //if (!isFlipped) isFlipped = true
                isFlipped = true // Ensures only flips when clicked
            }
            .graphicsLayer(
                rotationY = rotation, // Applies flip effect
                cameraDistance = 8f * density // Prevents distortion
            )


    ) {
        if (
            rotation < 90f
            //!isFlipped
            ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F8F8)
                      //    AppColors.SoftWhite
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 12.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = SubCatType.imageResId),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(1.dp,
                               Color(
                                    0xFFF3F3F3
                                    //  0xFFA9F9A9
                                )
                                  ),
                                    RoundedCornerShape(
                                        topStart = 8.dp,
                                        topEnd = 8.dp,
                                        bottomStart = 0.dp,
                                        bottomEnd = 0.dp,
                                    ),
                                )

                            .clip(RoundedCornerShape(
                                topStart = 8.dp,
                                topEnd = 8.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                            ))
                            .background(
                               // AppColors.SoftWhite
                                Color(0xFFF3F3F3)
                            )
                            .padding(8.dp)
                            .height(120.dp)

                    )
                    Text(
                        text = SubCatType.name,
                        fontSize = 22.sp,
                        color = AppColors.DarkGrey,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier

                            .fillMaxSize()
                            .border(BorderStroke(1.dp,
                                Color(
                                    0xFFF3F3F3
                                  //  0xFFA9F9A9
                                )
                            ), RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp,
                            ),)
                            .clip(RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 8.dp,
                            ))
                            .background(
                                AppColors.SoftWhite
                              //  Color(0xFFF3F3F3)
                            )
                            .padding(top = 8.dp, bottom = 8.dp),
                        lineHeight = 28.sp,
                        )
                }
            }
        } else {
                // Back Side (Flipped Card)
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(Color(0xFFBFFFA6)), // ✅ Set flipped background color
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 12.dp),
                    modifier = Modifier.fillMaxSize()
                        .align(Alignment.Center)
                        .padding(8.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(8.dp)
                        ) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            //  verticalArrangement = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(id = SubCatType.imageResId),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )

                            Text(
                                text = SubCatType.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.DarkGrey,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                        }
                    }
                }

                //  Show Dialog after flip is completed
                LaunchedEffect(Unit) {
                    showDialog.value = true
                }

        }
    }

    // Track `showDialog` state changes
    LaunchedEffect(showDialog.value) {
        if (!showDialog.value) {
            delay(100) // Small delay before flipping back
            isFlipped = false // ✅ Smoothly resets to original state
        }
    }


    //  Step 2: Show Dialog on Flip Completion
    if (showDialog.value) {
        Dialog(onDismissRequest = {
            showDialog.value = false
           // isFlipped = false //Reset card when dialog closes
        }) {
            Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    Modifier.padding(bottom = 16.dp).fillMaxWidth()
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFBFFFA6)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(SubCatType.imageResId),
                            contentDescription = "",
                            modifier = Modifier
                                .height(150.dp)
                                .padding(12.dp)
                        )
                    }
                    Column(
                        Modifier.padding(16.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = SubCatType.name.uppercase(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 48.sp
                        )
                        val points = SubCatType.description.split(".").filter { it.isNotBlank() }.map { it.trim() }
                        points.forEach { point ->
                            Text(
                                "• $point",
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(16.dp, 2.dp, 16.dp, 8.dp),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


/*
@Composable
fun SignsSupTypeCardSquare(SubCatType: SignSubType){

    val showDialog = remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable { showDialog.value = true }
        ,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          //  containerColor = Color(0xFFEEF0F5)
            AppColors.SoftWhite
          //  containerColor = colorResource(R.color.yellow_orange)
        )
    ) {

        Box(
            Modifier
                .requiredSize(width = 180.dp, height = 180.dp)
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(8.dp))
        ){

            Column(
                Modifier .align(Alignment.Center)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        //.border(2.dp, colorResource(id = R.color.white), RoundedCornerShape(12.dp)) // White outline,
                        .background(
                            AppColors.SoftWhite
                           // colorResource(id = R.color.white)
                        )
                ){
                    Image(
                        painter = painterResource(id = SubCatType.imageResId),
                        contentDescription = "",
                        Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .padding(8.dp)
                        ,
                        alignment = Alignment.Center,
                    )

                }


                Text(
                    text = SubCatType.name,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .background(
                           // color = Color(0xFFEEF0F5)
                            AppColors.SoftWhite
                            //colorResource(id = R.color.yellow_orange)
                        )
                        .fillMaxWidth(),
                    color = AppColors.DarkGrey,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center,

                )
            }
        }
    } // Card end

// Alert Dialog Box displayed
//    if (showDialog.value) {
//        AlertDialog(
//            onDismissRequest = { showDialog.value = false },
//            title = { Text(
//                SubCatType.name,
//                textAlign = TextAlign.Center,
//
//            ) },
//            text = {
//                Column {
//                    Image(
//                        painter = painterResource(id = SubCatType.imageResId),
//                        contentDescription = SubCatType.name,
//                        modifier = Modifier.size(128.dp)
//                    )
//                    Text("More details about ${SubCatType.name}...") // Add more details here
//                }
//            },
//            confirmButton = {
//                TextButton(onClick = { showDialog.value = false }) {
//                    Text("Close")
//                }
//            }
//        )
//    }

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
        ) {
            Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                Column (
                    Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                ){
                    Box(
                        Modifier
                            .padding(0.dp)
                            .fillMaxWidth()
                       //     .background(Color(0xFF89FF5A))
                            .background(Color(0xFFBFFFA6))
                        ,
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(SubCatType.imageResId),
                            contentDescription = "",
                         //   contentScale = ContentScale.Fit,
                            modifier = Modifier.height(150.dp)
                                .padding(12.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Column (
                        Modifier
                            .padding(
                                top = 8.dp,
                                bottom = 4.dp,
                                start = 16.dp,
                                end = 16.dp,
                                )
                            .fillMaxWidth(),
                    ){
                        Text(
                            text = SubCatType.name.uppercase(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 48.sp,
                            //Color = colorResource(id = R.color.black)
                            //fontSize = 24.dp,
                        )


                        val points = SubCatType.description.split(".").filter { it.isNotBlank() }.map { it.trim() } // Split into points
                        points.forEach { point ->
                            Text("• $point",
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(16.dp, 2.dp, 16.dp, 8.dp),
                                fontSize = 16.sp,

                            ) // Display each point

                        }
                    }

                }
            }
        }
    } //Dialog box ending

}


     */


//LazyColumn {
//        items(Signs_SubType){ SubCatType ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .size(100.dp)
//                    .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 2.dp)
//                    .height(70.dp),
//                shape = RoundedCornerShape(8.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = colorResource(R.color.yellow_orange)
//                )
//            ) {
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ){
//
//                    Column(
//                        modifier =  Modifier.padding(8.dp)
//                            .align(Alignment.CenterVertically),
//                    ) {
//
//                        Text(
//                            text = SubCatType.name,
//                            fontSize = 24.sp,
//                            modifier = Modifier
//                                .fillMaxWidth(.70f),
//                            color = Color(0xFF000000),
//                        )
//                    }
//                    Image(
//                        painter = painterResource(id = SubCatType.imageResId),
//                        contentDescription = "",
//                        Modifier
//                            .clip(RoundedCornerShape(12.dp))
//                            .padding(8.dp)
//                    )
//                }
//
//            }
//        }
//    }


@Composable
fun SignsSupTypeCardRect(SubCatType: SignSubType, onClick:() -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
            .padding(8.dp)
            .clickable(onClick = onClick)
        //    .height(70.dp)
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE985FF)
            //0xFFFF8585

        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            Row(
                modifier =  Modifier.padding(2.dp)
                    .align(Alignment.CenterVertically),
            ) {

                Image(
                    painter = painterResource(id = SubCatType.imageResId),
                    contentDescription = "",
                    Modifier
                        .fillMaxWidth(0.3f)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(8.dp)
                        .height(80.dp)
                        .align(Alignment.CenterVertically)
                )

                Column (
                    modifier = Modifier
                        .padding(0.dp,)
                        .height(180.dp)
                        .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                        .background(Color(0xFFEDEB6E))
                ) {
                    Text(
                        text = SubCatType.name,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        text = SubCatType.description,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Start,

                    )

                }


            }









        }

    }
}




@Composable
fun SignsSupTypeCard(SubCatType: SignSubType, onClick:() -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 2.dp)
            .clickable(onClick = onClick)
            .height(70.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.yellow_orange)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            Column(
                modifier =  Modifier.padding(8.dp)
                    .align(Alignment.CenterVertically),
            ) {

                Text(
                    text = SubCatType.name,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth(.70f),
                    color = Color(0xFF000000),
                )
            }
            Image(
                painter = painterResource(id = SubCatType.imageResId),
                contentDescription = "",
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(8.dp)
            )
        }

    }
}

data class SignSubType(
    val name: String,
    val imageResId : Int,
    val description : String,
)


fun SubCatType(category: String, SubCategoryList: String): List<SignSubType>{
    return when (category){
        "Mandatory Signs" -> when (SubCategoryList){
            "Control Signs" -> listOf(
                SignSubType("Stop Sign", R.drawable.mandatory_sign_img, "The Stop Sign is a regulatory traffic sign that instructs drivers to come to a complete halt at an intersection or crossing.. It is typically an octagonal red sign with white lettering that says \"STOP\"."),
                SignSubType("Way", R.drawable.prohibition_signs_img, ""),
                SignSubType("Stop Go Sign", R.drawable.stop_sign_img, ""),
                SignSubType("Way Go", R.drawable.compulsory_signs_img, "")
            )

            "Prohibition Signs" -> listOf(
                SignSubType("No Parking",R.drawable.stop_sign_img, ""),
                SignSubType("No Turn",R.drawable.stop_sign_img, "")
            )

            else -> emptyList()
        }

        else -> emptyList()
    }
}

@Preview
@Composable
fun SignsSupTypeCardPreview() {
    val category = SignSubType("Stop Sign", R.drawable.stop_sign_img, "")

    SignsSupTypeCard(SubCatType = category, onClick = {},)
}

@Preview
@Composable
fun SignsSupTypeCardSquarePreview() {
    val category = SignSubType("Stop Sign", R.drawable.mandatory_sign_img, "")

    SignsSupTypeCardSquare(SubCatType = category,)
}


@Preview
@Composable
fun SignsSupTypeCardRectPreview(){
    val category = SignSubType("Stop Sign", R.drawable.mandatory_sign_img, "The Stop Sign is a regulatory traffic sign that instructs drivers to come to a complete halt at an intersection or crossing.")

    SignsSupTypeCardRect(SubCatType = category, onClick = {},)
}
