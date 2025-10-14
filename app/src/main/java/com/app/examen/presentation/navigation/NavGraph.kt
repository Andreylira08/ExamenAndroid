package com.app.examen.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.examen.presentation.screens.detail.CountryDetailScreen
import com.app.examen.presentation.screens.home.HomeScreen

sealed class Screen(
    // Esta es la clase BASE define una propiedad route que representa ruta de navegación
    // Como es una sealed class solo puede tener las subclases que estan definidas dentro de ella
    val route: String,
) {
    object Home : Screen("home")

    // Hereda Screen Define una ruta con un parámetro {countryName}

    object Detail : Screen("country/{countryName}") {
        // Incluye una función createRoute que construye la ruta real reemplazando el parámetro con un Name específico

        fun createRoute(countryName: String) = "country/$countryName"
    }
}

@Composable
fun CountryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        // Contenedor que maneja todas nuestras pantallas

        navController = navController,// El controlador que maneja la navegación
        startDestination = Screen.Home.route,// Indica qué pantalla se muestra primero
        modifier = modifier, // Para personalizar el aspecto si es necesario
    ) {
        // PRIMERA PANTALLA: Home
        composable(route = Screen.Home.route) {
            // "route" es como la dirección de la pantalla
            HomeScreen(
                onCountryClick = { countryName ->
                    //Que hacer cuando se hace click en un país
                    // Navega a la pantalla de detalles pasando el name del País como argumento
                    navController.navigate(Screen.Detail.createRoute(countryName))
                },
            )
        }

        composable(
            route = Screen.Detail.route, // La ruta incluye el parametro countryName
            arguments = listOf(
                navArgument("countryName") {
                    type = NavType.StringType
                }
            ),
            // Define el tipo de dato es CountryName

        ) { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: "mexico"
            CountryDetailScreen(
                countryName = countryName,
                onBackClick =
                    // Funcion que realizara cuando se haga click en el boton de regresar
                     { navController.popBackStack() }, // Regresa a la pantalla anterior
            )
        }
    }
}