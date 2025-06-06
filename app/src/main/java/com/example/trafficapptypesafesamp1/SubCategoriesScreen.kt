package com.example.trafficapptypesafesamp1


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SubCategoriesScreen(navController: NavController, category: String){
    Log.d("SubCategoriesScreen", "Category: $category")

    val sub_Categories = subCategories(category)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.SoftWhite)
           // .background(Color(0xFFE6E6E6))
        ,
    ) {
        Text(
            text = category.uppercase(), // Displaying the category name as title
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp), // Adds padding to avoid cut-off
            verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between items
        ) {
            items(sub_Categories) { subCategory ->
                AnimatedVisibility(
                    visible = true, // Cards will always animate on first load
                    enter = fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.95f)
                ) {
                    SubCategoryCardRect1(subCategory) {
                        navController.navigate(Screen.SubCategoriesType.createRoute(category, subCategory.name))
                    }
                }
            }
        }



//        LazyColumn (
//            modifier = Modifier.fillMaxSize()
//        ){
//            items(sub_Categories){ subCategories ->
//                SubCategoryCardRect1(subCategories) {
//                    navController.navigate(Screen.SubCategoriesType.createRoute(category, subCategories.name))
//                }
//
//            }
//        }

    }

}

//    val variableName = functionCall(param1)
//
//    LazyColumn {
//        items(variableName) { item ->
//            CustomComposable(item) {
//                navController.navigate(destination)
//            }
//        }
//    }


@Composable
fun SubCategoryCardRect1(subCategories: SubCategoryList, onClick:() -> Unit,){

    var isPressed by remember { mutableStateOf(false) }
    var triggerNavigation by remember { mutableStateOf(false) } // Ensures first tap triggers navigation

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f, // Shrink & overshoot
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, // Bounce effect
            stiffness = Spring.StiffnessMedium
        ),
        label = "CardScaleAnimation"
    )

    LaunchedEffect(triggerNavigation) {
        if (triggerNavigation) {
            onClick()
            triggerNavigation = false // Reset state after navigation
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(8.dp)
//            .clickable(onClick = onClick)
        //    .height(70.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale) // Apply bounce effect
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                        triggerNavigation = true // Triggers navigation after animation
                    }
                )
            },

        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
            //AppColors.SoftWhite
            //colorResource(R.color.purple_200)
          //  AppColors.SuccessGreen
            // Color(0xFFE985FF)
            //Color(0xFFFF8585)
           // Color(0xFFFFFFFF)

            // colorResource(R.color.purple_200)
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier.padding(8.dp),
        ) {
            val imageModifiers = Modifier
                .height(80.dp)
                .size(100.dp)
                //.background(Color(0xFFF3F3F3))
                .border(BorderStroke(1.dp,
                    Color(0xFFF3F3F3)
                          //  Color(0xFFEDEB6E)
                ), RoundedCornerShape(
                    //12.dp,
//                    topEnd = 12.dp,
//                    bottomEnd = 12.dp,
                    topStart = 8.dp,
                    bottomStart = 8.dp
                ))
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF3F3F3))

                .fillMaxWidth(0.3f)
                .padding(8.dp)

            Image(
                painter = painterResource(id = subCategories.imageResID),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = imageModifiers
                // Modifier
//                    .fillMaxWidth(0.3f)
//                    .clip(RoundedCornerShape(12.dp))
//                    .padding(8.dp)
//                    .height(75.dp)
//                    //.border(BorderStroke(1.dp, Color(0xFFFFFFFF))
//                    .align(Alignment.CenterVertically)
            )

            Column (
                modifier = Modifier
                  //  .padding(8.dp)
                    .height(180.dp)
                    .border(BorderStroke(1.dp,
                       // Color(0xFF7B2BFF)
                      //    Color(0xFFEDEB6E)
                        //    Color(0xFFFFFFFF)
                        Color(0xFFF3F3F3)
                    ), RoundedCornerShape(
                        //12.dp,
                        topEnd = 8.dp,
                        bottomEnd = 8.dp,
                     //   topStart = 12.dp,
                     //   bottomStart = 12.dp
                    ))

                    .background(
                       // Color(0xFF7B2BFF)
                       // Color(0xFFFFFFFF)
                       // Color(0xFFBB86FC)
                    AppColors.SoftWhite
                     //   Color(0xFFEDEB6E)
                    )
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = subCategories.name,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
                    color = Color(
                      //  0xFFFFFFFF
                        0xFF000000
                    ),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                )

                Text(
                    text = subCategories.description,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                    color = Color(
                       // 0xFFFFFFFF
                           0xFF000000
                    ),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,

                    )

            }
        }

    }
}


@Composable
fun SubCategoryCardRect2(subCategories: SubCategoryList, onClick:() -> Unit,){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(8.dp)
            .clickable(onClick = onClick)
        //    .height(70.dp)
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            //colorResource(R.color.purple_200)
           // AppColors.SuccessGreen
             Color(0xFFC9FFD1)
            //Color(0xFFFF8585)
           // Color(0xFFFFFFFF)

            // colorResource(R.color.purple_200)
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier.padding(8.dp),
        ) {

            val imageModifiers = Modifier
                .height(80.dp)
                .size(100.dp)
                .border(BorderStroke(1.dp,
                   // Color(0xFF7B2BFF)
                      Color(0xFFEDEB6E)
                ), RoundedCornerShape(
                    //12.dp,
//                    topEnd = 12.dp,
//                    bottomEnd = 12.dp,
                    topStart = 12.dp,
                    bottomStart = 12.dp
                ))
                .fillMaxWidth(0.3f)
                .padding(8.dp)

            Image(
                painter = painterResource(id = subCategories.imageResID),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = imageModifiers
                // Modifier
//                    .fillMaxWidth(0.3f)
//                    .clip(RoundedCornerShape(12.dp))
//                    .padding(8.dp)
//                    .height(75.dp)
//                    //.border(BorderStroke(1.dp, Color(0xFFFFFFFF))
//                    .align(Alignment.CenterVertically)
            )

            Column (
                modifier = Modifier
                    //  .padding(8.dp)
                    .height(180.dp)
                    .border(BorderStroke(1.dp,
                       // Color(0xFF7B2BFF)
                        //  Color(0xFFEDEB6E)
                      Color(0xFFFFFFFF)
                    ), RoundedCornerShape(
                        //12.dp,
                        topEnd = 12.dp,
                        bottomEnd = 12.dp,
                           topStart = 12.dp,
                        //   bottomStart = 12.dp
                    ))

                    .background(
                        // Color(0xFF7B2BFF)
                        Color(0xFFFFFFFF)
                        // Color(0xFFBB86FC)
                        //AppColors.SuccessGreen.copy()
                        //   Color(0xFFEDEB6E)
                    )
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = subCategories.name,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
                    color = Color(
                        //  0xFFFFFFFF
                        0xFF000000
                    ),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                )

                Text(
                    text = subCategories.description,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                    color = Color(
                        // 0xFFFFFFFF
                        0xFF000000
                    ),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,

                    )

            }
        }

    }
}


@Composable
fun SubCategoryCard(subCategories: SubCategoryList, onClick:() -> Unit, ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 2.dp)
            .clickable(onClick = onClick),
            //.height(70.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple_200))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            Column(
                modifier =  Modifier.padding(8.dp)
                    .align(Alignment.CenterVertically),
            ) {

                Text(
                    text = subCategories.name,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth(.70f),
                    color = Color(0xFF000000),
                )
            }
            Image(
                painter = painterResource(id = subCategories.imageResID),
                contentDescription = "",
                Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))

            )
        }

    }

}

@Composable
fun SubCategoryCardRect(subCategories: SubCategoryList, onClick:() -> Unit,){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(8.dp)
            .clickable(onClick = onClick)
        //    .height(70.dp)
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            //colorResource(R.color.purple_200)
            AppColors.SuccessGreen
            // Color(0xFFE985FF)
            // 0xFFFF8585
            // colorResource(R.color.purple_200)
        )
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =  Modifier.padding(4.dp),
            ) {

                Image(
                    painter = painterResource(id = subCategories.imageResID),
                    contentDescription = "",
                    Modifier
                        .fillMaxWidth(0.3f)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(8.dp)
                        .height(75.dp)
                        .align(Alignment.CenterVertically)
                )

                Column (
                    modifier = Modifier
                        .padding(0.dp,)
                        .height(180.dp)
                        .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                        .background(
                            Color(0xFFFFFFFF)
                            //Color(0xFFBB86FC)
                            //AppColors.SuccessGreen.copy()
                            //Color(0xFFEDEB6E)
                        )
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = subCategories.name,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 2.dp, start = 8.dp, end = 8.dp),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(
                        text = subCategories.description,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,

                        )

                }
            }

    }
}


data class SubCategoryList(
    val name: String,
    val imageResID: Int,
    val description: String,
)


fun subCategories(category: String) : List<SubCategoryList> {
    return when (category){
        "Mandatory Signs" -> listOf (
            SubCategoryList("Control Signs", R.drawable.control_signs_img, "Regulate traffic movement"),
            SubCategoryList("Prohibition Signs", R.drawable.prohibition_signs_img, "Restrict specific actions"),
            SubCategoryList("Restriction Signs", R.drawable.restriction_signs_img, "Impose limits"),
            SubCategoryList("Compulsory Signs", R.drawable.compulsory_signs_img, "Instruct mandatory actions"),
            SubCategoryList("Parking & Stopping Signs", R.drawable.compulsory_signs_img, "Regulate vehicles"),
        )

        "Cautionary Signs" -> listOf(
            SubCategoryList("Road Layout Signs", R.drawable.control_signs_img, ""),
            SubCategoryList("Hazard Signs", R.drawable.prohibition_signs_img, "")
        )

        else -> emptyList()
    }
}


@Preview
@Composable
fun SubCategoryCardPreview() {
    val category = SubCategoryList("Control Signs", R.drawable.control_signs_img, "")

    SubCategoryCard(subCategories = category, onClick = {},)
}

@Preview
@Composable
fun SubCategoryCardRectPreview() {
    val category = SubCategoryList("Control Signs", R.drawable.control_signs_img, "Regulate traffic movement")

    SubCategoryCardRect(subCategories = category, onClick = {},)
}

@Preview
@Composable
fun SubCategoryCardRect1Preview() {
    val category = SubCategoryList("Control Signs", R.drawable.control_signs_img, "Regulate traffic movement")

    SubCategoryCardRect1(subCategories = category, onClick = {},)
}

@Preview
@Composable
fun SubCategoryCardRect2Preview() {
    val category = SubCategoryList("Control Signs", R.drawable.control_signs_img, "Regulate traffic movement")

    SubCategoryCardRect2(subCategories = category, onClick = {},)
}



//@Preview
//@Composable
//fun SubCatCardPreview(){
//    Column(
//        Modifier.verticalScroll(rememberScrollState())
//    ) {
//        subCategories(category = toString()).forEach{
//                subCategories ->
//            SubCategoryCard(subCategories, onClick = {})
//        }
//}
//}