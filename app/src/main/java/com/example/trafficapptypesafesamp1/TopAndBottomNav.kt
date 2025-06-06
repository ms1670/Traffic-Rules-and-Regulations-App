@file:Suppress("UNREACHABLE_CODE")

package com.example.trafficapptypesafesamp1

import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.pager.*
import com.google.accompanist.pager.*
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.outlined.Category
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController

@Preview
@Composable
fun TopAndBottomNav(){
    val navController = rememberNavController()
    var selected by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = selected) // Sync with pager
    val coroutineScope = rememberCoroutineScope()
    var isInsideSubpage by remember { mutableStateOf(false) }

    // Track whether to reset Categories navigation
    var resetCategories by remember { mutableStateOf(false) }

    // Sync selected state when swiping (Avoids extra recompositions)
    LaunchedEffect(pagerState.currentPage) {
        selected = pagerState.currentPage
    }

    Column {
        Scaffold(

            topBar = {
                TopAppBar()
            },

            bottomBar = {
                BottomAppBar(selected, navController)
              //  BottomAppBar(selected, onItemSelected = { selected = it })
                { newIndex ->
                   // selected = newIndex
                    coroutineScope.launch {
                     //   pagerState.animateScrollToPage(newIndex)
                        pagerState.scrollToPage(newIndex)
                    }

                    //  Reset Categories when clicking Bottom Navigation
                    if (newIndex == 1) {
                        resetCategories = true  // Tell CategoriesNavigation to reset
                    }

//                    if (newIndex == 1) { // When clicking Categories
//                        navController.navigate(Screen.MainCategories.route) {
//                            popUpTo(Screen.MainCategories.route) { inclusive = true }
//                        }
//                    }

//                    if (newIndex != 1) { // If not on Categories tab
//                        navController.navigate("mainCategories") {
//                            popUpTo("mainCategories") { inclusive = true }
//                        }
//                    }

                }
            }
        )
        {paddingValues ->

            HorizontalPager(
                count = 4,
                state = pagerState,
                modifier = Modifier.padding(paddingValues),
                userScrollEnabled = !isInsideSubpage
            ) {page ->
              //  selected = page
                when (page){
                    0 -> HomeScreenNav()
//                    1 -> CategoriesScreen { isInside ->
//                        isInsideSubpage = isInside }
                     1 -> CategoriesScreen (
                         setInsideSubpage = { isInsideSubpage = it },
                         resetCategories = resetCategories
                     )
                    2 -> TrafficRulesScreen(navController)
                    3 -> AdditionalScreen()
                }

            }

//            Box(
//                modifier = Modifier
//                    .padding(paddingValues)
//            ) {
//
//                when (selected){
//                    0 -> HomeScreenNav()
//                    1 -> TrafficSignNavigation()
//                    2 -> TrafficRulesScreen(navController)
//                    3 -> Text("")
//
//                }

//                when (selected){
//                    0 -> Text("")
//                    1 -> Text("")
//                    2 -> Text("")
//                    3 -> Text("")
//
//                }

               // TrafficSignNavigation()
            }
        }


    }





@Preview
@Composable
fun TopAppBar() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    data class MenuItems(
        var name: String
    )

    var MenuExpandList = listOf(
        MenuItems("App Info"),
        MenuItems("Settings"),
    )

    Box(
        Modifier.fillMaxWidth()
            .background(
                AppColors.TrafficYellow
               // Color(0xFFD32F2F)
            )
        //0xFFFF5757 - king of orange and red
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(.45f)
                .height(60.dp)
                .align(Alignment.Center)
        )

        Row (
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = {
                    Toast.makeText(context, "Search", Toast.LENGTH_LONG).show()
                },
                //   modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Default.Search, contentDescription = "")
            }


            IconButton(
                onClick = { expanded = !expanded },

            ) {
                Icon(Icons.Default.MoreVert, contentDescription = "")
            }

        }



        val density = LocalDensity.current
        val screenWidth = with(density) { LocalConfiguration.current.screenWidthDp.dp }

        DropdownMenu(
            expanded = expanded,
            offset = DpOffset(screenWidth / 2 - (-85).dp, 0.dp),
            modifier = Modifier
                .align(Alignment.TopEnd),
            onDismissRequest = { expanded = false }
        ) {
            MenuExpandList.forEach{MenuItems ->
                DropdownMenuItem(
                    text = { Text(MenuItems.name) },
                    onClick =   {
                        expanded = false
                        Toast.makeText(context, MenuItems.name, Toast.LENGTH_LONG).show()
                    }   // Handle menu item click here

                    )
            }

        }
    }

}


@Composable
fun BottomAppBar(selected: Int, navController: NavController, onItemSelected: (Int) -> Unit) {

    var shouldResetCategories by remember { mutableStateOf(false) } //  State to trigger reset

    val rememberedNavController = remember(navController) { navController }

    //  Reset navigation when `shouldResetCategories` becomes true
//    LaunchedEffect(shouldResetCategories) {
//        if (shouldResetCategories) {
//            ResetCatsBottomNav(navController = rememberedNavController)
//            shouldResetCategories = false //  Reset state after navigation
//        }
//    }

//    DisposableEffect(shouldResetCategories) {
//        if (shouldResetCategories) {
//            ResetCatsBottomNav(navController = navController) // ✅ Directly call it
//            shouldResetCategories = false // ✅ Reset state after navigation
//        }
//        onDispose { }
//    }


    NavigationBar(
        modifier = Modifier
            .graphicsLayer {
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
                clip = true
            },
        containerColor = AppColors.TrafficYellow.copy(0.2f)
    ) {
        BottomAppBarItem.forEachIndexed { index, BottomNavItems ->
            NavigationBarItem(
                selected = index == selected, // Now properly updates based on `selected`
                onClick = {
                  //  onItemSelected(index)

                    if (index == selected && BottomNavItems.route == "categories") {
                        // Force reset when clicking the same tab again
                        shouldResetCategories = true
                    } else {
                        onItemSelected(index)
                    }

                    if (BottomNavItems.route == Screen.MainCategories.route) {
                        navController.navigate(Screen.MainCategories.route) {
                            popUpTo(Screen.MainCategories.route) { inclusive = true }
                        }
                    }

//                    if (BottomNavItems.route == "categories") {
//                        navController.navigate("mainCategories") {
//                            popUpTo("mainCategories") { inclusive = true }
//                        }
//                    }
                          },
                icon = {
                    Box(
                        modifier = Modifier
                            .background(
                                color =
                                if (index == selected)
                                    AppColors.TrafficYellow.copy(alpha = 0.4f)
                                else
                                    Color.Transparent,
                                shape =
                                if (index == selected)
                                    RoundedCornerShape(
                                        12.dp
                                    ) else
                                    RoundedCornerShape(0.dp)
                                // Optional: rounded background
                            )

                            .padding(
                                start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp
                            ) // Padding for better appearance)

                    ) {
                        BadgedBox(
                            badge = {
                                if (BottomNavItems.badges != 0) {
                                    Badge {
                                        Text(text = BottomNavItems.badges.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (index == selected)
                                    BottomNavItems.selectedIcon
                                else
                                    BottomNavItems.unselectedIcon,
                                contentDescription = BottomNavItems.title,
                                tint = if (index == selected)
                                    AppColors.WarningAmber
                                else
                                    AppColors.trafficSub2DarkGray
                            )
                        }
                    }

                },
                label = {
                    Text(
                        text = BottomNavItems.title,
                        fontWeight = if (index == selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (index == selected) AppColors.DarkGrey else AppColors.trafficSub2DarkGray,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.WarningAmber,
                    unselectedIconColor = AppColors.trafficSub2DarkGray,
                    selectedTextColor = AppColors.WarningAmber,
                    unselectedTextColor = AppColors.trafficSub2DarkGray,
                    indicatorColor = Color.Transparent // Properly sets the selection indicator
                )
            )
        }
    }
}


@Composable
fun ResetCatsBottomNav(navController: NavController) {
//    navController.navigate("main_categories?reset=${System.currentTimeMillis()}") {
//        popUpTo("main_categories") { inclusive = true }
//    }

    try {
        navController.navigate("main_categories") {
            popUpTo("main_categories") { inclusive = true }
        }
    } catch (e: Exception) {
        Log.e("NavigationError", "Failed to reset CategoriesScreen: ${e.message}")
    }

}

//@Composable
//fun BottomAppBar(selected: Int, onItemSelected: (Int) -> Unit){
//
//    val navController = rememberNavController()
//    var selected by remember { mutableStateOf(0) }
//
//    NavigationBar(
//        modifier = Modifier
//            .graphicsLayer {
//                shape = RoundedCornerShape(
//                    topStart = 16.dp,
//                    topEnd = 16.dp
//                )
//                clip = true
//            },
//        containerColor = AppColors.TrafficYellow.copy(0.2f)
//    ) {
//        BottomAppBarItem.forEachIndexed { index, BottomNavItems ->
//            NavigationBarItem(
//                index == selected,
//                onClick = { onItemSelected(index) },
//                icon = {
//                    Box(
//                        modifier = Modifier
//
//                            .background(
//                                color =
//                                if (index == selected)
//                                    AppColors.TrafficYellow.copy(alpha = 0.4f)
//                                else
//                                    Color.Transparent,
//                                shape =
//                                if (index == selected)
//                                    RoundedCornerShape(
//                                        12.dp
//                                    ) else
//                                    RoundedCornerShape(0.dp)
//                                // Optional: rounded background
//                            )
//
//                            .padding(
//                                start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp
//                            ) // Padding for better appearance)
//
//                        //                                        .clickable( // Remove default highlight effect
//                        //                                            interactionSource = remember { MutableInteractionSource() },
//                        //                                            indication = null
//                        //                                        ) {}
//
//                    ) {
//                        BadgedBox(
//                            modifier = Modifier
//                                .padding(0.dp),
//                            badge = {
//                                if (BottomNavItems.badges != 0) {
//                                    Badge {
//                                        Text(text = BottomNavItems.badges.toString())
//                                    }
//                                }
//                            }
//                        ) {
//                            Icon(
//                                imageVector = if (index == selected)
//                                    BottomNavItems.selectedIcon
//                                else
//                                    BottomNavItems.unselectedIcon,
//                                contentDescription = BottomNavItems.title,
//                                tint = if (index == selected)
//                                //AppColors.TrafficYellow else Color.Gray
//                                    AppColors.WarningAmber else AppColors.trafficSub2DarkGray
//                                //Color(AppColors.WarningAmber.toArgb()) else AppColors.trafficSub2DarkGray
//                            )
//                        }
//                    }
//                },
//
//
//                label = {
//                    Text(
//                        text = BottomNavItems.title,
//                        color = if (index == selected) AppColors.WarningAmber else AppColors.trafficSub2DarkGray,
//                        modifier = Modifier
//                            .padding(bottom = 8.dp),
//                    )
//                },
//
//
//                alwaysShowLabel = true,
//                // interactionSource = remember { MutableInteractionSource() }, // Removes default selection effect
//                // indication = null // Disables default highlight / ripple effect
//
//                colors = NavigationBarItemDefaults.colors( // Override default selection effect
//                    selectedIconColor = AppColors.WarningAmber,
//                    unselectedIconColor = AppColors.trafficSub2DarkGray,
//                    selectedTextColor = AppColors.TrafficYellow,
//                    unselectedTextColor = AppColors.trafficSub2DarkGray,
//                    indicatorColor =
//                    // AppColors.TrafficYellow.copy(alpha = 0.5f)
//                    Color.Transparent // Removes the unwanted background highlight
//
//                )
//            )
//        }
//    }
//}




@Composable
fun BottomAppBar1(){

    var selected by remember { mutableStateOf(0) }

    NavigationBar{
        BottomAppBarItem.forEachIndexed { index, BottomNavItems ->
            NavigationBarItem(selected = index == selected,
                onClick = {
                    selected = index
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (BottomNavItems.badges != 0) {
                                Badge {
                                    Text(text = BottomNavItems.badges.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selected)
                                BottomNavItems.selectedIcon
                            else
                                BottomNavItems.unselectedIcon,
                            contentDescription = BottomNavItems.title
                        )
                    }
                },
                label = {
                    Text(text = BottomNavItems.title)
                })

        }
    }





}

data class BottomNavItems(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badges: Int
)

val BottomAppBarItem = listOf(
    BottomNavItems(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        badges = 0,
    ),

    BottomNavItems(
        title = "Categories",
        route = "categories",
        selectedIcon = Icons.Filled.Category,
        unselectedIcon = Icons.Outlined.Category,
       // selectedIcon = Icons.AutoMirrored.Filled.List,
       // unselectedIcon = Icons.AutoMirrored.Outlined.List,
        badges = 0,
    ),

    BottomNavItems(
        title = "Test",
        route = "test",
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
        badges = 0,
    ),

    BottomNavItems(
        title = "Profile",
        route = "profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        badges = 0,
    ),


    )


//
//AnimatedDropdownMenu(expanded) { expanded = false }
//}
//}
//
//@Composable
//fun AnimatedDropdownMenu(expanded: Boolean, onDismiss: () -> Unit) {
//    val transition = updateTransition(targetState = expanded, label = "menu transition")
//
//    val menuOffset by transition.animateDp(
//        transitionSpec = { tween(durationMillis = 300) }, label = "offset"
//    ) { isExpanded ->
//        if (isExpanded) 0.dp else (-10).dp // Moves down when expanded
//    }
//
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = onDismiss,
//        modifier = Modifier.background(Color.White),
//        offset = DpOffset(0.dp, menuOffset) // Controls downward animation
//    ) {
//        listOf("App Info", "Settings").forEach { item ->
//            DropdownMenuItem(
//                text = { Text(item) },
//                onClick = onDismiss
//            )
//        }
//    }
//}