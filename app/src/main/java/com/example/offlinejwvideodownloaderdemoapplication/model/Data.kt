package com.example.offlinejwvideodownloaderdemoapplication.model

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("beans"                    ) var beans                    : ArrayList<Beans> = arrayListOf(),
    @SerializedName("totalPages"               ) var totalPages               : Int?             = null,
    @SerializedName("totalElements"            ) var totalElements            : Int?             = null,
    @SerializedName("currentPageNo"            ) var currentPageNo            : Int?             = null,
    @SerializedName("currentPageElementsCount" ) var currentPageElementsCount : Int?             = null,
    @SerializedName("pageSize"                 ) var pageSize                 : Int?             = null
)
