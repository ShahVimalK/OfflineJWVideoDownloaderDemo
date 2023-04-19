package com.example.offlinejwvideodownloaderdemoapplication.model

import com.google.gson.annotations.SerializedName

data class DemoDataClass(@SerializedName("data"     ) var data     : Data?    = Data(),
                         @SerializedName("success"  ) var success  : Boolean? = null,
                         @SerializedName("response" ) var response : String?  = null,
                         @SerializedName("message"  ) var message  : String?  = null)
