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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.events.SearchEvent
import com.rodrigoguerrero.myweather.ui.viewmodels.SearchViewModel
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.manage_locations
import myweather.shared.generated.resources.saved_locations
import myweather.shared.generated.resources.search_hint
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun SearchScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val viewModel = koinInject<SearchViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(state.isCompleted) {
        if (state.isCompleted) {
            onClose()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchField(
                query = state.query,
                focusRequester = focusRequester,
                onQueryUpdated = { viewModel.onEvent(SearchEvent.QueryUpdated(it)) },
                onClear = { viewModel.onEvent(SearchEvent.ClearQuery) },
                onBack = onClose,
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

@OptIn(ExperimentalResourceApi::class)
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
            text = stringResource(Res.string.saved_locations),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(Res.string.manage_locations),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            modifier = Modifier
                .clickable {
                    onManage()
                    keyboardController?.hide()
                }
        )
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

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
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SearchField(
    query: String,
    onQueryUpdated: (String) -> Unit,
    onClear: () -> Unit,
    onBack: () -> Unit,
    focusRequester: FocusRequester,
) {
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
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            placeholder = {
                Text(text = stringResource(Res.string.search_hint))
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
