package com.example.trafficapptypesafesamp1

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun TrafficSignNavigation(setInsideSubpage: (Boolean) -> Unit, resetCategories: Boolean){
    val navController = rememberNavController()

    // Reset navigation when `resetCategories` is true
    LaunchedEffect(resetCategories) {
        if (resetCategories) {
            navController.navigate("main_categories") {
                popUpTo("main_categories") { inclusive = true }
            }
        }
    }

    // Reset navigation instantly when `resetCategories` is true
//    SideEffect {
//        if (resetCategories) {
//            navController.navigate("main_categories") {
//                popUpTo("main_categories") { inclusive = true }
//            }
//        }
//    }

    NavHost(navController = navController, startDestination = "main_categories") {

        // Main Categories Screen
        composable("main_categories?reset={reset}",
            arguments = listOf(navArgument("reset") { defaultValue = "0" }),
//            enterTransition = { fadeIn(animationSpec = tween(100)) },
//            exitTransition  = { fadeOut(animationSpec = tween(100)) },
        ) {
            setInsideSubpage(false)
            MainCategoriesScreen(navController)
        }

        // Sub Categories Screen
        composable(
            "sub_categories/{MainCategory}",
            arguments = listOf(navArgument("MainCategory") { type = NavType.StringType }),
//            enterTransition = { fadeIn(animationSpec = tween(150)) },
//            exitTransition  = { fadeOut(animationSpec = tween(150)) },
        ) { backStackEntry ->
            val MainCategory = backStackEntry.arguments?.getString("MainCategory") ?: ""
            setInsideSubpage(true)
            SubCategoriesScreen(navController, MainCategory)
        }

        // Sub Categories Types Screen
        composable(
            "SubCategories_Type/{MainCategory}/{SubCategoryList}",
            arguments = listOf(
                navArgument("MainCategory") { type = NavType.StringType },
                navArgument("SubCategoryList") { type = NavType.StringType },
            ),
//            enterTransition = { fadeIn(animationSpec = tween(150)) },
//            exitTransition  = { fadeOut(animationSpec = tween(150)) },
        ) { backStackEntry ->
            val MainCategory = backStackEntry.arguments?.getString("MainCategory") ?: ""
            val SubCategoryList = backStackEntry.arguments?.getString("SubCategoryList") ?: ""
            setInsideSubpage(true)
            SubCategoriesTypeScreen(navController, MainCategory, SubCategoryList)
        }

    }
}


sealed class Screen(val route: String){
    object MainCategories : Screen("main_categories")

    data class SubCategories(val category: String) : Screen("sub_categories/{MainCategory}"){
        companion object{
            fun createRoute(category: String) = "sub_categories/$category"
        }
    }

    data class SubCategoriesType(val category: String, val subCategory: String) : Screen("SubCategories_Type/{MainCategory}/{SubCategoryList}") {
        companion object {
            fun createRoute(MainCategory: String, SubCategoryList: String) = "SubCategories_Type/$MainCategory/$SubCategoryList"
        }
    }


}