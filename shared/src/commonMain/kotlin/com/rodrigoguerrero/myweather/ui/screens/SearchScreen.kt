package com.rodrigoguerrero.myweather.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.rodrigoguerrero.myweather.ui.models.events.SearchEvent
import com.rodrigoguerrero.myweather.ui.viewmodels.SearchViewModel
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onNewLocationSelected: (String) -> Unit,
) {
    val navigator = LocalNavigator.currentOrThrow
    val focusRequester = remember { FocusRequester() }
    val viewModel = getViewModel(
        key = "search-screen",
        factory = viewModelFactory { SearchViewModel() }
    )
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(state.isCompleted) {
        if (state.isCompleted) {
            navigator.pop()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchField(
                query = state.query,
                focusRequester = focusRequester,
                onQueryUpdated = { viewModel.onEvent(SearchEvent.QueryUpdated(it)) },
                onClear = { viewModel.onEvent(SearchEvent.ClearQuery) }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
        ) {
            itemsIndexed(state.searchSuggestions) { index, suggestion ->
                SearchSuggestion(
                    text = suggestion.toString(),
                    onSelected = {
                        onNewLocationSelected(suggestion.toString())
                        viewModel.onEvent(SearchEvent.SuggestionSelected(suggestion))
                    },
                    withDivider = index < state.searchSuggestions.size - 1,
                )
            }

            if (state.favoriteLocations.isNotEmpty()) {
                item {
                    FavoriteLocationsHeader(
                        onManage = { viewModel.onEvent(SearchEvent.OnManageFavoriteLocations) },
                    )
                }
            }

            items(state.favoriteLocations) { favoriteLocation ->
                SearchSuggestion(
                    text = favoriteLocation.location,
                    onSelected = {
                        onNewLocationSelected(favoriteLocation.location)
                        viewModel.onEvent(SearchEvent.OnFavoriteSelected(favoriteLocation))
                    },
                    showRemoveIcon = state.showRemoveFavoritesIcon,
                    onRemove = {
                        viewModel.onEvent(SearchEvent.RemoveFavoriteLocation(favoriteLocation))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun FavoriteLocationsHeader(
    modifier: Modifier = Modifier,
    onManage: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(MR.strings.saved_locations),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(MR.strings.manage_locations),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            modifier = Modifier
                .clickable {
                    onManage()
                    keyboardController?.hide()
                }
        )
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchSuggestion(
    text: String,
    onSelected: () -> Unit,
    onRemove: () -> Unit = { },
    withDivider: Boolean = true,
    showRemoveIcon: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                keyboardController?.hide()
                onSelected()
            }
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            overflow = TextOverflow.Clip,
            modifier = Modifier.weight(1f),
            maxLines = 1,
        )

        if (showRemoveIcon) {
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                )
            }
        }
    }
    if (withDivider) {
        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(
    query: String,
    onQueryUpdated: (String) -> Unit,
    onClear: () -> Unit,
    focusRequester: FocusRequester,
) {
    val navigator = LocalNavigator.currentOrThrow
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = query,
            onValueChange = onQueryUpdated,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            leadingIcon = {
                IconButton(onClick = { navigator.pop() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            placeholder = {
                Text(text = stringResource(MR.strings.search_hint))
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = onClear) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                }
            }
        )
    }
}
