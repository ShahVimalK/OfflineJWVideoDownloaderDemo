package com.example.offlinejwvideodownloaderdemoapplication.model

import androidx.annotation.Keep

@Keep
data class JwPlayerApiData(
    val description: String,
    val feed_instance_id: String,
    val kind: String,
    val playlist: List<Playlist>,
    val title: String
)

@Keep
data class Playlist(
    val description: String,
    val duration: Int,
    val image: String,
    val images: List<Image>,
    val link: String,
    val mediaid: String,
    val pubdate: Int,
    val sources: List<Source>,
    val title: String
)

@Keep
data class Image(
    val src: String, val type: String, val width: Int
)

@Keep
data class Source(
    val drm: Drm, val file: String, val type: String
)

@Keep
data class Drm(
    val widevine: Widevine
)

@Keep
data class Widevine(
    val url: String
)