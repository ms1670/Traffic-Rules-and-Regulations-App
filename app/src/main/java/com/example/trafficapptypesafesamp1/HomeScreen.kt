package com.example.trafficapptypesafesamp1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreenNav(){
    HomeScreen(mainCategories)
}


@Composable
fun HomeScreen(mainCategories: List<MainCategory>) {

    var expandedHomeMainCat by remember { mutableStateOf(true) }
    var expandedHomeMoreCat by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
            .background(Color(0xFFFFFFFF))
            .padding(12.dp)
    ) {
        Row(
            Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.placehold_img),
                contentDescription = "",
                Modifier
                    .clip(RoundedCornerShape(16.dp))
            )
        }
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

//            Spacer(modifier = Modifier
//                .height(4.dp)
//            )

            Text(
                text = "Traffic Rules and Signs",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )

            Text(
                text = "Traffic Signs or Road Signs are signs erected at the side of roads to provide information to road users.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )

            Spacer(
                modifier = Modifier
                    .height(2.dp)
            )

            Text(
                text = "Categories in Traffic Signs",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Expandable list with cat title dropdown

            ExpandableMainCategoryList(
                title = "Primary Categories",
                category = mainCategories.take(5),
                expanded = expandedHomeMainCat,
                onExpandChange = { expandedHomeMainCat = !expandedHomeMainCat }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExpandableMainCategoryList(
                title = "Extended Categories",
                category = mainCategories.drop(5),
                expanded = expandedHomeMoreCat,
                onExpandChange = { expandedHomeMoreCat = !expandedHomeMoreCat }
            )

        }
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(
                text = "Learn about Traffic Rules",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))


        }





    }
}

            // Old codes of list of categories in list view
//            val splitIndex = 5
//
//            Text(
//                text = "Main Categories",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.SemiBold,
//                lineHeight = 24.sp,
//            )
//
//            mainCategories.take(splitIndex).forEach { category ->
//                Text(
//                    text = "• ${category.name}",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Normal,
//                    lineHeight = 28.sp
//                )
//            }
//
//            Spacer(modifier = Modifier
//                .height(8.dp)
//            )
//
//            Text(
//                text = "More Categories",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.SemiBold,
//                lineHeight = 24.sp,
//            )
//
//            mainCategories.drop(splitIndex).forEach { category ->
//                Text(
//                    text = "• ${category.name}",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Normal,
//                    lineHeight = 28.sp
//                )
//            }


//        //General Rules
//
//        Column (
//            modifier = Modifier.padding(12.dp)
//        ) {
//            Text(
//                text = "General Rules",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                lineHeight = 28.sp
//            )
//
//            Card (
//                modifier = Modifier.padding(8.dp)) {
//                Box(){
//
//                    Image(
//                        painter = painterResource(id = R.drawable.placehold_img),
//                        contentDescription = "",
//                        Modifier
//                            .clip(RoundedCornerShape(16.dp))
//                    )
//
//                    Text(
//                        text = "Carousel Card / Swappable Cards",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        color = Color(0xFF050505),
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(topStart = 16.dp, bottomEnd = 15.dp))
//                            .padding(12.dp)
//                            .background(Color(0xD0FFFFFF))
//                            .height(24.dp)
//                            .align(Alignment.BottomCenter)
//                            .wrapContentHeight()
//                    )
//
//                }
//
//            }
//        }


@Composable
fun ExpandableMainCategoryList(title: String, category: List<MainCategory>, expanded: Boolean, onExpandChange: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF67FFF4))
                    .clickable{onExpandChange()}
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier.size(24.dp),
                )
            }


                AnimatedVisibility(visible = expanded) {
                    Row (
                        modifier = Modifier
                            .background(Color(0xFF67FFF4))
                            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFE0FFFD))
                                .padding(start = 16.dp, bottom = 4.dp),
                        ) {
                            category.forEach{ category ->
                                Text(
                                    text = "• ${category.name}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 28.sp,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )

                            }
                        }
                    }


                }
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview(){
   // val category = MainCategory("Mandatory Signs", R.drawable.mandatory_sign_img)

    HomeScreen(mainCategories)
}


//non-Independent expandable category lists

//
//@Composable
//fun HomeScreen(mainCategories: List<MainCategory>) {
//    var expandedMain by remember { mutableStateOf(false) }
//    var expandedMore by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxHeight()
//            .background(Color(0xFFFFFFFF))
//            .padding(12.dp)
//    ) {
//        // Header Text
//        Text(
//            text = "Traffic Rules and Signs",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            lineHeight = 28.sp
//        )
//
//        Text(
//            text = "Traffic Signs or Road Signs are signs erected at the side of roads to provide information to road users.",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,
//            lineHeight = 20.sp
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Main Categories Dropdown
//        ExpandableCategoryList(
//            title = "Main Categories",
//            categories = mainCategories.take(5),
//            expanded = expandedMain,
//            onExpandChange = { expandedMain = !expandedMain }
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // More Categories Dropdown
//        ExpandableCategoryList(
//            title = "More Categories",
//            categories = mainCategories.drop(5),
//            expanded = expandedMore,
//            onExpandChange = { expandedMore = !expandedMore }
//        )
//    }
//}
//
//@Composable
//fun ExpandableCategoryList(title: String, categories: List<MainCategory>, expanded: Boolean, onExpandChange: () -> Unit) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(4.dp),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Column {
//            // Header with Dropdown Icon
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { onExpandChange() }
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = title,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Icon(
//                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
//                    contentDescription = "Expand",
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            // Animated Visibility for Expanding List
//            AnimatedVisibility(visible = expanded) {
//                Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)) {
//                    categories.forEach { category ->
//                        Text(
//                            text = "• ${category.name}",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Normal,
//                            lineHeight = 28.sp,
//                            modifier = Modifier.padding(vertical = 4.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}



//Independent expandable category lists

//
//@Composable
//fun HomeScreen(mainCategories: List<MainCategory>) {
//    // Ensure both dropdowns are collapsed initially
//    var expandedMain by remember { mutableStateOf(true) }
//    var expandedMore by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxHeight()
//            .background(Color.White)
//            .padding(12.dp)
//    ) {
//        // Traffic Rules & Signs Header
//        Text(
//            text = "Traffic Rules and Signs",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            lineHeight = 28.sp
//        )
//
//        Text(
//            text = "Traffic Signs or Road Signs are signs erected at the side of roads to provide information to road users.",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,
//            lineHeight = 20.sp
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // ✅ Expandable Main Categories (Initially Collapsed)
//        ExpandableCategoryList(
//            title = "Main Categories",
//            categories = mainCategories.take(5),
//            expanded = expandedMain,
//            onExpandChange = { expandedMain = !expandedMain } // Only expands when clicked
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // ✅ Expandable More Categories (Initially Collapsed)
//        ExpandableCategoryList(
//            title = "More Categories",
//            categories = mainCategories.drop(5),
//            expanded = expandedMore,
//            onExpandChange = { expandedMore = !expandedMore } // Only expands when clicked
//        )
//    }
//}
//
//@Composable
//fun ExpandableCategoryList(title: String, categories: List<MainCategory>, expanded: Boolean, onExpandChange: () -> Unit) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(4.dp),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Column {
//            // Clickable Header with Expand/Collapse Icon
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { onExpandChange() } // Toggle expansion
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = title,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Icon(
//                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
//                    contentDescription = "Expand",
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            // Expandable List with Smooth Animation
//            AnimatedVisibility(visible = expanded) {
//                Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)) {
//                    categories.forEach { category ->
//                        Text(
//                            text = "• ${category.name}",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Normal,
//                            lineHeight = 28.sp,
//                            modifier = Modifier.padding(vertical = 4.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
