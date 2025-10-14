package com.app.examen.data.local.preferences


//Este archivo nos permitirá guardad algunas constantes para el sistema de cache, tanto para poder identificar
// como los guardamos, y el tiempo de validez que tendrá la caché antes de realizar una nueva petición al API en internet.
object PreferencesConstants {
    const val PREF_NAME = "country_preferences"
    const val KEY_COUNTRY_CACHE = "country_cache"
    const val KEY_LAST_COUNTRY = "last_country"
    const val KEY_LAST_UPDATE = "last_update"
    const val KEY_TOTAL_COUNT = "total_count"
    const val CACHE_DURATION = 5 * 60 * 1000 // 5 minutos en milisegundos
}