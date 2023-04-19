package com.example.offlinejwvideodownloaderdemoapplication

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinejwvideodownloaderdemoapplication.model.Beans
import com.example.offlinejwvideodownloaderdemoapplication.model.DemoDataClass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jwplayer.pub.api.drm.DrmDownloadManager
import com.jwplayer.pub.api.drm.MediaDownloadOption
import com.jwplayer.pub.api.drm.OfflineDrmFactory
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity(), JWMediaExtractor.JsonDownloadListener, ListAdapter.CustomListeners {

    private val TAG = "JWPlayerDownload"
    private val JWP_CDN_REQUEST_URL = "https://dev-backend.studyiq.net/" + "app-content-ws/v1/drm/get/license/jwplayer/"
    private var mDrmDownloadManager: DrmDownloadManager? = null
    private var jwExtractor: JWMediaExtractor? = null
    private var apiService: ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDrmDownloadManager = OfflineDrmFactory.getDrmDownloadManagerFor(this, "b8kkCUo6")
        jwExtractor = JWMediaExtractor(this, this)

        val retrofit = Retrofit.Builder()
            .baseUrl(JWP_CDN_REQUEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        val content = getContent(this.assets)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        content?.data?.beans?.let {
            val adapter = ListAdapter(this, it)
            recyclerView.adapter = adapter
        }
    }

    private fun getContent(assetManager: AssetManager): DemoDataClass? {
        val gson = Gson()
        val listType = object : TypeToken<DemoDataClass?>() {}.type
        val fileName = "data.json"
        return gson.fromJson(
            readJsonFromAssets(
                assetManager,
                fileName
            ), listType
        )
    }

    private fun readJsonFromAssets(assetManager: AssetManager, fileName: String): String? {
        val json: String = try {
            val `is` = assetManager.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }

    override fun onJsonDownloadComplete(item: PlaylistItem, offline: Boolean) {
        Log.d(TAG, "onJsonDownloadComplete: $item")
        mDrmDownloadManager?.prepareMediaDownload(this, item, this)
    }

    override fun onApiFailure(error: String?) {
        Log.d(TAG, "onApiFailure: $error")
    }

    override fun selectedQuality(
        videoQuality: MediaDownloadOption,
        audioQuality: MediaDownloadOption?
    ) {
        Log.d(TAG, "selectedQuality: videoQuality -> " + videoQuality.label + " , audioQuality -> " + audioQuality?.label)
        mDrmDownloadManager?.downloadMedia(this, videoQuality, audioQuality)
    }

    override fun onMediaDownloadFailed(e: Exception?) {
        Log.d(TAG, "onMediaDownloadFailed: Exception -> ${e?.printStackTrace()}")
    }

    override fun onDownloadOptionsAvailable(
        video: List<MediaDownloadOption?>?,
        audio: List<MediaDownloadOption?>?
    ) {
        jwExtractor?.showOptionDialog(video as MutableList<MediaDownloadOption>,
            audio as MutableList<MediaDownloadOption>?
        )
    }

    override fun onDownloadComplete(mediaId: String?) {
        Toast.makeText(baseContext, "$mediaId is downloaded", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onDownloadComplete: mediaId -> $mediaId")
    }

    override fun onDownloadUpdate(percentage: Float) {
        Log.d(TAG, "onDownloadUpdate: $percentage")
    }

    override fun onClickListener(item: Beans) {
        apiService?.let { jwExtractor?.cdnRequest(it, JWP_CDN_REQUEST_URL, item.courseId, item.mediaId) }
    }
}