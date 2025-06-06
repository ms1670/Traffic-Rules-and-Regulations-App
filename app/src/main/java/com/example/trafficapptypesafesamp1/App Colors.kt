package com.example.trafficapptypesafesamp1

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.example.trafficapptypesafesamp1.AppColors.DarkGrey
import com.example.trafficapptypesafesamp1.AppColors.SoftWhite
import com.example.trafficapptypesafesamp1.AppColors.TrafficRed
import com.example.trafficapptypesafesamp1.AppColors.TrafficYellow

object AppColors {

    val TrafficRed = Color(0xFFD32F2F)
    val TrafficYellow = Color(0xFFFFA000)
    val SoftWhite = Color(0xFFFAFAFA)
    val DarkGrey = Color(0xFF121212)

    val trafficSub2DarkGray = Color(0xFF4F4F4F)

    val SuccessGreen = Color(0xFF388E3C)
    val WarningAmber = Color(0xFFFF6F00)
    val ErrorRed = Color(0xFFD32F2F)
  //  val ErrorRed1 = Color(0xFFF9CB7E)

//    val trafficYellow = Color(0xFFFFA000)
//    val trafficWhite = Color(0xFFFAFAFA)
//    val trafficDarkGrey = Color(0xFF121212)
//    val trafficSub1DarkGray = Color(0xFF5D5D5D)
//    val trafficSub2DarkGray = Color(0xFF4F4F4F)
//    val trafficSuccessGreen = Color(0xFF388E3C)

}

val LightColors = lightColorScheme(
    primary = TrafficRed,
    secondary = TrafficYellow,
    background = SoftWhite,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color.Black
)

val DarkColors = darkColorScheme(
    primary = TrafficRed,
    secondary = TrafficYellow,
    background = DarkGrey,
    surface = Color.Black,
    onPrimary = Color.White,
    onBackground = Color.White
)