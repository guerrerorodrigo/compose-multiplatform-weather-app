package com.rodrigoguerrero.myweather

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.rodrigoguerrero.myweather.ui.navigation.RootNavGraph
import com.rodrigoguerrero.myweather.ui.theme.AppTheme
import okio.FileSystem

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        RootNavGraph()
    }
}

fun getAsyncImageLoader(context: PlatformContext) = ImageLoader.Builder(context)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .memoryCache {
        MemoryCache.Builder()
            .maxSizePercent(context, 0.3)
            .strongReferencesEnabled(true)
            .build()
    }.diskCachePolicy(CachePolicy.ENABLED)
    .networkCachePolicy(CachePolicy.ENABLED)
    .diskCache {
        newDiskCache()
    }.crossfade(true)
    .logger(DebugLogger())
    .build()

fun newDiskCache(): DiskCache = DiskCache.Builder()
    .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
    .maxSizeBytes(1024L * 1024 * 1024) // 512MB
    .build()
