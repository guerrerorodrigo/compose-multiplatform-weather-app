package com.rodrigoguerrero.myweather.ui.models.uimodels

data class SearchSuggestion(
    val name: String,
    val region: String,
    val country: String,
    val id: Long,
) {

    override fun toString(): String {
        return "$name, $region, $country"
    }
}
