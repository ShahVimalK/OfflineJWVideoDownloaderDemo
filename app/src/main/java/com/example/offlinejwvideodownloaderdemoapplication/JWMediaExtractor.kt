package com.example.offlinejwvideodownloaderdemoapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinejwvideodownloaderdemoapplication.model.JwPlayerApiData
import com.example.offlinejwvideodownloaderdemoapplication.model.JwPlayerUrlData
import com.jwplayer.pub.api.configuration.DrmConfig
import com.jwplayer.pub.api.drm.MediaDownloadOption
import com.jwplayer.pub.api.drm.MediaDownloadResultListener
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JWMediaExtractor(private val listener: JsonDownloadListener, private val context: Context) {

    companion object {
        private const val TAG = "JWPlayerDownload"
    }

    interface JsonDownloadListener : MediaDownloadResultListener {
        fun onJsonDownloadComplete(item: PlaylistItem, offline: Boolean)
        fun onApiFailure(error: String?)
        fun selectedQuality(videoQuality: MediaDownloadOption, audioQuality: MediaDownloadOption?)
    }

    fun cdnRequest(apiService: ApiService, url: String, courseId: Int, mediaId: String) {
        val call: Call<JwPlayerUrlData> = apiService.getJwPlayerUrl(url, courseId, mediaId, true)
        call.enqueue(object : Callback<JwPlayerUrlData> {
            override fun onResponse(
                call: Call<JwPlayerUrlData>,
                response: Response<JwPlayerUrlData>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.success) {
                            jsonRequest(apiService, it.response)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<JwPlayerUrlData>, t: Throwable) {

            }

        })
    }

    private fun jsonRequest(apiService: ApiService, cdn: String) {
        val call: Call<JwPlayerApiData> = apiService.getJwPlayerData(cdn)
        call.enqueue(object : Callback<JwPlayerApiData> {
            override fun onResponse(
                call: Call<JwPlayerApiData>,
                response: Response<JwPlayerApiData>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        preparePlayListItem(it)
                    }
                }
            }

            override fun onFailure(call: Call<JwPlayerApiData>, t: Throwable) {}

        })
    }

    private fun preparePlayListItem(data: JwPlayerApiData) {
        data.let {

            val firstData = it.playlist[0]

            val title = firstData.title
            val desc = firstData.description
            val image = firstData.image
            val mediaId = firstData.mediaid

            val file = firstData.sources[0].file
            val licence = firstData.sources[0].drm.widevine.url

            Log.d(TAG, "preparePlayListItem: file => $file")
            Log.d(TAG, "preparePlayListItem: licence => $licence")

            val builder = PlaylistItem.Builder().apply {
                title(title)
                description(desc)
                image(image)
            }
            builder.mediaId(mediaId)
            builder.file(file)
            builder.drmConfig(DrmConfig.Builder().licenseUri(licence).build())
            listener.onJsonDownloadComplete(builder.build(), true)
        }
    }

    fun showOptionDialog(
        videoQualities: MutableList<MediaDownloadOption>,
        audioQualities: MutableList<MediaDownloadOption>?
    ) {

        val items = ArrayList<String>()
        videoQualities.forEach { video ->
            items.add(video.label)
        }

        val selectedItem = -1

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Download ")

        val adapter = ItemAdapter(context, items)
        adapter.setSelectedItem(selectedItem)

        // Set the adapter for the recycler view
        val recyclerView = RecyclerView(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        builder.setView(recyclerView)

        builder.setPositiveButton(
            "Ok"
        ) { _: DialogInterface?, _: Int ->
            if (adapter.getSelectedItem() != -1) {
                listener.selectedQuality(
                    videoQualities[adapter.getSelectedItem()], audioQualities?.get(0)
                )
            }
        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE)?.setTextColor(Color.BLACK)
            dialog.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(Color.BLACK)
        }
        dialog.show()
    }


}