package com.example.trafficapptypesafesamp1

import androidx.compose.runtime.Composable

@Composable
fun CategoriesScreen(setInsideSubpage: (Boolean) -> Unit, resetCategories: Boolean){
    TrafficSignNavigation(setInsideSubpage, resetCategories)
}