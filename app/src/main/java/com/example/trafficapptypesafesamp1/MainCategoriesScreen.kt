package com.example.trafficapptypesafesamp1

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainCategoriesScreen(navController: NavController){

    val splitIndex = 5

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
           // .background(AppColors.TrafficYellow.copy(alpha = 0.3f)),
    ) {
//        items(mainCategories) {mainCategories ->
//            MainCategoryCard(mainCategories) {
//                navController.navigate(Screen.SubCategories.createRoute(mainCategories.name))
//            }
//        }
        stickyHeader {
            Box (
               
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.SuccessGreen.copy(0.9f)) // Ensure solid background
               // .padding(horizontal = 0.dp, vertical = 0.dp)
                ,

               // color = AppColors.SuccessGreen, // Ensures a solid background color
               // shadowElevation = 4.dp, // Adds a shadow effect to make it stand out

            ) {
                Text(
                    text = "Primary Categories".uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    color = AppColors.DarkGrey,
                    modifier = Modifier
//                        .background(
//                            AppColors.SuccessGreen.copy(0.8f)
//                            //   Color.Cyan
//                        )
                        .fillMaxWidth()
                        .padding(start = 12.dp, top = 12.dp, bottom = 8.dp)
                )
            }
        }

        items(mainCategories.take(splitIndex)) {mainCategories ->
            MainCategoryCard(mainCategories) {
                navController.navigate(Screen.SubCategories.createRoute(mainCategories.name))
            }
        }


        stickyHeader {

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.SuccessGreen.copy(0.9f)) // Ensure solid background
                // .padding(horizontal = 0.dp, vertical = 0.dp)
            ) {
                Text(
                    text = "Extended Categories".uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    color = AppColors.DarkGrey,
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(
//                            AppColors.SuccessGreen.copy(0.8f)
//                            //   Color.Cyan
//                        )
                        .padding(start = 12.dp, top = 12.dp, bottom = 8.dp)
                )
            }

        }

        items(mainCategories.drop(splitIndex)) {mainCategories ->
            MainCategoryCard(mainCategories) {
                navController.navigate(Screen.SubCategories.createRoute(mainCategories.name))
            }
        }



    }
}


@Composable
fun MainCategoryCard(mainCategories: MainCategory, onClick:() -> Unit){

    var isPressed by remember { mutableStateOf(false) }

    var triggerNavigation by remember { mutableStateOf(false) } // Ensures first tap triggers navigation

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f, // Shrink effect on click
        animationSpec = tween(durationMillis = 100),
        label = "CardScaleAnimation"
    )

    LaunchedEffect(triggerNavigation) {
        if (triggerNavigation) {
            onClick()
            triggerNavigation = false // Reset state after navigation
        }
    }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .size(100.dp)
            .padding(top = 8.dp, bottom =  8.dp, start = 12.dp, end = 12.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
//            .clickable(
//                onClick = onClick,
//                onClickLabel = "Go to Subcategories",
////                interactionSource = remember { MutableInteractionSource() },
////                indication = rememberRipple(bounded = true)  // Adds ripple effect
//            )

            // called "Press State Scaling" or "Tap Feedback Scaling".
            // Touch Feedback Animation / Tap Shrink Effect / Scale Press Effect / Clickable Press Animation
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                        triggerNavigation = true
                    }
                )
            },
           // .height(60.dp)

        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
           // AppColors.SoftWhite
                    containerColor = Color(0xFFF8F8F8)
            //AppColors.TrafficYellow
        // containerColor = Color(0xFFFFFFFF)
        // containerColor = Color(0xFFf9cb7e) FFA000
        //containerColor = colorResource(R.color.teal_200),
        ),

        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp
        )

    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier.padding(8.dp),
        ){
            Column (

                modifier = Modifier
                   // .weight(1f)
                    .fillMaxHeight()
                   // .align(Alignment.CenterVertically)
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
                    .clip(RoundedCornerShape(topStart = 8.dp,
                        bottomStart = 8.dp))
                    .background(
                    AppColors.SoftWhite
                     // Color(0xFFFFFFFF)
                        //Color(0xFF7B2BFF)
                       //  Color(0xFFF8F8F8)
                 //        Color(0xFFF1F1F1)
                        // Color(0xFFBB86FC)
                        //AppColors.SuccessGreen.copy()
                        //   Color(0xFFEDEB6E)
                    )
                    ,

                  //  .padding(8.dp)

                horizontalAlignment = Alignment.CenterHorizontally, // Ensures text is centered inside the column
                verticalArrangement = Arrangement.Center // Centers text vertically inside Column

            ) {

                Text(
                    text = mainCategories.name,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                     //   .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(.75f),
                    maxLines = 2,
                    color = AppColors.DarkGrey,
                    // color = Color(0xFF000000),
                    overflow = TextOverflow.Ellipsis,

                )

            }

            Column (
            //    modifier =  Modifier
                    //.weight(1f)
               //     .padding(8.dp),
               // horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                val imageModifiers = Modifier
                    .height(80.dp)
                    .size(100.dp)

                    .border(BorderStroke(1.dp,
                        Color(0xFFF3F3F3)
                        //  Color(0xFFEDEB6E)
                    ), RoundedCornerShape(
                        //12.dp,
                    topEnd = 8.dp,
                    bottomEnd = 8.dp,
  //                      topStart = 12.dp,
//                        bottomStart = 12.dp
                    ))

                    .clip(
                        RoundedCornerShape(
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,

                        ))
                    .background(Color(0xFFF3F3F3))
                    .fillMaxWidth()
                    // .fillMaxWidth(0.3f)
                    .padding(8.dp)

                Image(
                    painter = painterResource(id = mainCategories.imageResID),
                    contentDescription = "",
                    modifier =  imageModifiers
//                        .border(
//                            BorderStroke(1.dp,
//                                Color(0xF35252FF)
//                                //  Color(0xFFEDEB6E)
//                            ), RoundedCornerShape(
//                                //12.dp,
//                                topEnd = 12.dp,
//                                bottomEnd = 12.dp,
//                                topStart = 12.dp,
//                                bottomStart = 12.dp
//                            ))
//                        .clip(RoundedCornerShape(12.dp))
//                        .padding(8.dp)

                    //  .background(AppColors.TrafficYellow)
                )

            }


        }
    }

}

data class MainCategory (
    val name: String,
    @DrawableRes val imageResID: Int,
)

val mainCategories = listOf(
    MainCategory("Mandatory Signs", R.drawable.mandatory_sign_img),
    MainCategory("Cautionary Signs", R.drawable.cautionary_sign_img),
    MainCategory("Informatory Signs", R.drawable.cautionary_sign_img),
    MainCategory("Facility Informatory Signs", R.drawable.cautionary_sign_img),
    MainCategory("Parking Signs", R.drawable.cautionary_sign_img),
    MainCategory("Temporary Traffic Signs", R.drawable.cautionary_sign_img),
    MainCategory("Traffic Light Signals", R.drawable.cautionary_sign_img),
    MainCategory("Road Markings", R.drawable.cautionary_sign_img),
    MainCategory("Electronic & Variable Message Signs", R.drawable.cautionary_sign_img),
    MainCategory("Tourism & Cultural Signs", R.drawable.cautionary_sign_img),
    MainCategory("Environmental Signs", R.drawable.cautionary_sign_img),
    MainCategory("Emergency Signs", R.drawable.cautionary_sign_img),
    MainCategory("Tunnel & Bridge Signs", R.drawable.cautionary_sign_img),
    MainCategory("Highway-Specific Signs", R.drawable.cautionary_sign_img),
    MainCategory("Smart Traffic Systems", R.drawable.cautionary_sign_img),
    MainCategory("Local / Contextual Signs", R.drawable.cautionary_sign_img),
    MainCategory("Educational / Awareness Signs", R.drawable.cautionary_sign_img),
)




















@Preview
@Composable
fun MainCategoryCardPreview() {
    val category = MainCategory("Mandatory Signs", R.drawable.mandatory_sign_img)

    MainCategoryCard(mainCategories = category, onClick = {})
}

@Preview
@Composable
fun MainCategoryCardPreview1() {
    val category = MainCategory("Electronic & Variable Message Signs", R.drawable.mandatory_sign_img)

    MainCategoryCard(mainCategories = category, onClick = {})
}

//@Preview
//@Composable
//fun MainCatPreview(){
//    Column(
//        Modifier.verticalScroll(rememberScrollState())
//    ) {
//        mainCategories.forEach{
//            mainCategories ->
//            MainCategoryCard(mainCategories, onClick = {})
//        }
//    }
//}